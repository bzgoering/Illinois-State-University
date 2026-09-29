#include <stdio.h>
#include <stdlib.h>

int main()
{
    FILE *file;
    char *filename = "/ben/foo";

    /*Open the following file for writing*/
    file = fopen("filename", "w");
    if (file == NULL){
        fprintf(stderr, "File %s could not be open\n", filename);
        exit(1);
    }

    /*write to the file*/
    fprintf(file, "Hello World!/n");

    /*close file*/
    fclose(file);

    FILE *file;
    char *filename = "/ben/foo";

    /*Open the following file for reading*/
    file = fopen("filename", "r");
    if (file == NULL){
        fprintf(stderr, "File %s could not be open\n", filename);
        exit(1);
    }

    /*read to the file*/
    while (1){
        char buffer[80];
        fgets(buffer, 80, file);

        /*end of file case*/
        if (feof(file))
            break;
        
        printf("%s", buffer);
    }

    /*close file*/
    fclose(file);
}
