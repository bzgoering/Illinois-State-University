// Program to perform spell checking
// The program will take 3 command line parameters, the names of two input files and one output file.
// The input files will be
//      1) a list of correctly spelled words, which will be stored in a dictionary
//      2) a file to check for correct spelling
// The output file will show the structure of the dictionary that was created (done for grading purposes)
// The program will output a list of incorrectly spelled words and suggestions for each word.
// By Mary Elaine Califf and Ben Goering

#include "Dictionary.h"
#include <iostream>
#include <fstream>
#include <ctype.h>
using namespace std;

// function prototypes

// opens and reads the word list input file, building the dictionary
void buildDictionary(const string& inFileName, Dictionary& dict);

// opens the input file and checks the spelling of the input file, producing output to standard out
void checkSpelling(const string& inFileName, const Dictionary& dict);

// open the dictionary structure file and write the structure of the dictionary to it
void writeDictionaryStructure(const string& outFileName, const Dictionary& dict);

// your additional function prototypes go here
// literates through word character looking for a suggestion fix for words not in dictionary; outputs words that are one letter away, swapped characters, or extra letters exclusivly.
void findSuggestions(string word, const Dictionary& dict);

int main(int argc, char** argv)
{
    // gather the file name arguments
    if (argc < 4)
    {
        // we didn't get enough arguments, so complain and quit
        cout << "Usage: " << argv[0] << " wordListFile inputFile dictionaryStructureFile" << endl;
        exit(1);
    }

    string wordListFileName = argv[1];
    string inputFileName = argv[2];
    string dictFileName = argv[3];

    Dictionary dict; // set up the dictionary

    // build the dictionary
    buildDictionary(wordListFileName, dict);

    // write the dictionary to the concordance file
    checkSpelling(inputFileName, dict);

    // write the dictionary structure to the dictionary structure file
    writeDictionaryStructure(dictFileName, dict);
}

void buildDictionary(const string& inFileName, Dictionary& dict)
{
    string curWord = "";

    // open the file
    ifstream infile(inFileName);
    if (infile.fail())
    {
        cerr << "Could not open " << inFileName << " for input" << endl;
        exit(1);
    }

    // YOUR CODE GOES HERE -- feel free to also add additional function(s) if desired
    //literates through the file of words listed
    while (getline(infile,curWord))
    {
        string wordBuild = "";
        
        //ensures no spaces are part of the word and lowercase each character
        for (char character : curWord)
        {
            character = tolower(character);
            
            //ensures its a [a-z]
            if (character >= 'a' && character <= 'z')
            {
                wordBuild += character;
            }
            //treats words before a non letter as its own word within the line
            //ex: C.P.U become 3 seperate "words"
            else if (wordBuild != "")
            {
                if (!dict.findEntry(wordBuild))
                {
                    //only adds if they dont already exist in tree
                    dict.addEntry(new string(wordBuild));
                }

                wordBuild = "";
            }
        }
        //makes word when end of line is reached
        if (wordBuild != "")
        {
            if (!dict.findEntry(wordBuild))
            {
                //only adds if they dont already exist in tree
                dict.addEntry(new string(wordBuild));
            }
        }
    }
    // close the file
    infile.close();
}

void checkSpelling(const string& inFileName, const Dictionary& dict)
{
    int lineNum = 1;
    string curWord = "";
    char character;

    // open the file
    ifstream infile(inFileName);
    if (infile.fail())
    {
        cerr << "Could not open " << inFileName << " for input\n";
        return;
    }

    // YOUR CODE GOES HERE -- you will want to add additional function(s) to further break down this logic 
    
    while (infile.get(character))
    {
        character = tolower(character);

        //begins checking word when full word if formed
        if ((!isalpha(character)) && curWord != "")
        {

            //looks for word in tree
            if (!dict.findEntry(curWord) && curWord != "\n")
            {
                cout<<curWord<<" on line "<<lineNum<<endl;
                findSuggestions(curWord, dict);
            }
            //resets curword
            curWord = "";
        }

        //new line
        if (character == '\n')
        {
            lineNum++;
        }
        
        //gets words from text, ingores anything else
        if (isalpha(character))
        {
            curWord += character;
        }
    }
    infile.close();
}

void writeDictionaryStructure(const string& outFileName, const Dictionary& dict)
{
    ofstream outfile(outFileName);
    if (outfile.fail())
    {
        cerr << "Could not open " << outFileName << " for output\n";
        cerr << "Dictionary structure file not written " << endl;
        return;
    }
    //dict.printDictionaryKeys(outfile);
    dict.printDictionaryInOrder(outfile);
    outfile.close();
}

// your additional function implementations go here

void findSuggestions(string word, const Dictionary& dict)
{
    bool found = false;
    string orgWord = word;
    string result = "Suggested corrections:";

    //rule #1: add one letter in each indicie
    for (int x = 0; x < word.size(); x++)
    {
        //traverse through each character to add
        for (int y = 'a'; y <= 'z'; y++)
        {  
            char temp = y;
            word = word.substr(0,x) + temp + word.substr(x,word.size());
            
            //adds found words to result
            if (dict.findEntry(word))
            {
                result += "\n    " + word;
                found = true;
            }

            //reset word
            word = orgWord;
        }
    }
    //rule #2: remove a letter
    for (int x = 0; x < word.size(); x++)
    {
        //removes one letter
        word = word.substr(0,x) + word.substr(x+1);
        //adds found words to result
        if (dict.findEntry(word))
        {
            result += "\n    " + word;
            found = true;
        }
        //reset word
        word = orgWord;
    }
    //rule #3: swap with adjacent
    for (int x = 0; x < word.size()-1; x++)
    {
        //swap with adjacent
        char temp = word[x+1];
        word[x+1] = word[x];
        word[x] = temp;

        //adds found words to result
        if (dict.findEntry(word))
        {
            result += "\n    " + word;
            found = true;
        }
        //reset word
        word = orgWord;
    }

    //output
    //no recommendations found
    if (!found)
    {
     cout<<"No suggestions found"<<endl;
    }
    else
    {
        cout<<result<<endl;
    }
}