#include <stdlib.h>
#include <stdio.h>

int main()
{
    int *buffer;
    int i;

    buffer = malloc(10 * sizeof(int));

    for (i = 0; i < 10; i++)
    {
        buffer[i] = i;
    }

    for (i = 0; i<10; i++)
    {
        printf("p[%d] is %d\n", i, buffer[i]);
    }

    free(buffer);
}
