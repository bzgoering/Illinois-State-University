#include <stdio.h>
#include <stdlib.h>

int main()
{
    //variables for reading input numbers
    int inputNum = 10;
    char *buffer;
    int num[inputNum];
    buffer = malloc(10 * sizeof(int));

    //gets input
    for(int x = 0; x < inputNum; x++)
    {
        fgets(buffer, sizeof(buffer), stdin);
        num[x] = atoi(buffer);
    }
    
    //sorts input using insertion sort
    for(int x = 0; x < inputNum-1; x++)
    {
        int select = x;

        for(int y = select+1; y < inputNum; y++)
        {
            if (num[y] < num[select])
            {
                select = y;
            }
        }   

        if (select != num[x])
        {
            int temp = num[x];
            num[x] = num[select];
            num[select] = temp;
        }
    }

    //output numbers to file and terminal
    FILE *file;
    char *filename = "output.txt";

    /*Open the following file for writing*/
    file = fopen("filename", "w");
    if (file == NULL){
        fprintf(stderr, "File %s could not be open\n", filename);
        exit(1);
    }

    /*write to the file*/
    for(int x = 0; x < inputNum; x++)
    {
        fprintf(file, "%d\n", num[x]);
        printf("%d\n", num[x]);
    }

    /*close file*/
    fclose(file);
}
