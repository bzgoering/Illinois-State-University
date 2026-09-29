//By: Ben Goering
#include <stdio.h>
#include <stdlib.h>
#include <fcntl.h>
#include <unistd.h>

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
            if (num[y] > num[select])
            {
                select = y;
            }
        }

        if (select != x)
        {
            int temp = num[x];
            num[x] = num[select];
            num[select] = temp;
        }
    }

    //output numbers to file and terminal
    FILE *file;
    char *filename = "sorted.txt";

    /*Open the following file for writing*/
    int fd = open(filename, O_WRONLY|O_CREAT|O_TRUNC, 0755);
    if(fd == -1){
        fprintf(stderr, "File %s could not be open\n", filename);
        exit(1);
    }

    /*write to the file*/
    for(int x = 0; x < inputNum; x++)
    {
        int len = sprintf(buffer, "%d\n", num[x]);
        write(fd,buffer,len);
        printf("%d\n", num[x]);
    }

    /*close file*/
    close(fd);
    free(buffer);
}
