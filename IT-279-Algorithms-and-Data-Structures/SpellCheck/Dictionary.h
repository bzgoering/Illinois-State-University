// An dictionary created using a basic binary search tree
// By Ben Goering
#include <string>
#include <vector>

using namespace std;

#ifndef DICTIONARY_H
#define DICTIONARY_H

class Dictionary
{
private:
    string** dictArray;
    int arraySize;
    int dataNum;
    static const int prime[14];
    static const int secPrime[14];
    int hashCount = 0;

public:
    // Constructor
    Dictionary();

    // Copy constructor
    Dictionary(const Dictionary& orig) { copy(orig); }

    // Destructor
    ~Dictionary() { clear(); }

    // Assignment operator
    Dictionary& operator=(const Dictionary& rhs);

    // Check to see if the Dictionary isEmpty
    bool isEmpty() const;

    // Add an entry
    // Precondition: the dictionary must not have the identical string already stored in the dictionary
    // Postcondition: the string has been added to the dictionary
    void addEntry(std::string* anEntry);

    // Determine whether the string is in the dictionary
    // Returns true if the string is stored in the dictionary and false otherwise
    bool findEntry(const std::string& data) const;

    // Print entries in order
    // Calls printEntry on each Entry in order
    void printDictionaryInOrder(std::ostream& outputStream) const;

    // Prints the dictionary keys only, demonstrating the dictionary structure
    // For the binary search tree, this is an easy to do tree format
    void printDictionaryKeys(std::ostream& outputStream) const;

private:
    // recursive clear helper
    void clear();
    
    // copy helper method for destructor and assignment operator
    void copy(const Dictionary& orig);
    
    // recursive helper method for insertion
    void probe(string* data);

    int secHashVal(const string& data, int index)const;

    void rehash();

    unsigned int getHashValue(const string & key)const;

    unsigned int getIncrement(const string & key) const;

    unsigned int getValue(const string & key) const;

    vector<string> getInOrder() const;
};

#endif