//By: Ben Goering

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <ctype.h>

//counts the amount of words and lines based on spaces and \n
void count(FILE *file)
{  
    //variables
    int numWord = 0; //counter for words
    int numLine = 1; //counter for lines
    int inword = 0; //boolean to ensure correct word count
    int character;  //character in file

    //loops through characters until it reaches the end
    while((character = fgetc(file)) != EOF)
    {
        //new line is found
        if (character == '\n')
        {
            numLine++;
        }

        //checks for space, avoids counting if its a double space
        if (isspace(character))
        {
            inword = 0;
        }
        else if(inword == 0)
        {
            inword = 1;
            numWord++;
        }
    }

    //prints results
    printf("Number of Lines: %d \nNumber of Words: %d \n", numLine,numWord);
}

int main()
{
    //gets filename
    char *filename;
    filename = malloc(100 * sizeof(char));
    printf("Enter file name: ");
    fgets(filename, 100, stdin);
    //strips the new line character
    filename[strcspn(filename,"\n")] = '\0';

    //grabs data from file
    FILE *file;

    /*Open the following file for writing*/
    file = fopen(filename, "r");
    if (file == NULL){
        fprintf(stderr, "File %s could not be open\n", filename);
        exit(1);
    }

    //reads file
    count(file);

    /*close file*/
    fclose(file);
    free(filename);
}
