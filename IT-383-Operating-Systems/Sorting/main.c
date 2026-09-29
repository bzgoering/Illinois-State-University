#include <stdio.h>
#include <stdlib.h>
#include <pthread.h>
#include <time.h>

//maxium number of integers that will be read
#define MAX_SIZE 100000000

//struct used to organize threads
typedef struct thread_chunk
{
    int *data;
    int SIZE;
    int start;
    int end;
}thread_chunk;

//reads the header of file, getting the size of integers
int read_header(FILE *read_file)
{
    //gets first line
    char line[256];

    //validation
    if (fgets(line, sizeof(line),read_file) == NULL)
    {
        printf("Error - something is wrong with file\n");
        return -1;
    }
    
    //process data
    int total_data;

    //validation
    if (sscanf(line,"%% %d", &total_data) != 1)
    {
        printf("Error - ensure format is \"%% some_integer\"\n");
        return -1;
    }
    return total_data;
}

//reads the integers and stores in array
int read_body(FILE *file_read, int *data, int size)
{
    char line[256];
    int count = 0;

    //parse through the rest of file
    while (fgets(line,sizeof(line),file_read) != NULL)
    {
        //ensures we have room in data
        if (count > size-1)
        {
            printf("Error - ensure header number is correct");
            return 0;
        }

        //turns string -> int and stores
        if (sscanf(line,"%d", &data[count]) != 1)
        {
            printf("Error at line %d - ensure format is \"some_integer\"\n", count+2);
            return 0;
        }

        count++;
    }

    return 1;
}

//compare function for qsort()
int compare(const void *x, const void *y) 
{
    return (*(int*)x - *(int*)y);  
}

//calculates the median of the integers read
double median(int *data, int SIZE)
{
    if (SIZE%2 != 0)
    {
        return (data[SIZE/2]); //gets middle number
    }
    else
    {
        long long math = (long long)data[(SIZE/2)-1] + (long long)data[SIZE/2]; //gets average of 2 middle numbers
        return math/2.0;
    }
}

//calculates the mode of the integers read
double mode(int *data, int SIZE)
{
    double mode = data[0];
    int max_count = 1;

    int current = data[0];
    int count = 1;

    //loops through data, looking for a longer set of same integers
    for (int i = 1; i < SIZE; i++)
    {
        if (data[i] == current)
        {
            count++;
        }
        else
        {
            if (count > max_count)
            {
                max_count = count;
                mode = current;
            }

            current = data[i];
            count = 1;
        }
    }

    if (count > max_count)
    {
        mode = current;
    }

    return mode;
}

//calculates the mean/average of the integers
double mean(int *data, int SIZE)
{
    double avg = 0;

    for(int x = 0; x<SIZE; x++)
    {
        avg += data[x];
    }
    return avg/SIZE;
}

//gets the median, mode, and mean and prints them
void compute(int *data, int SIZE)
{
    double median_value = median(data, SIZE);
    printf("Median: %.2f\n",median_value);

    double mode_value = mode(data, SIZE);
    printf("Mode: %.2f\n",mode_value);

    double mean_value = mean(data, SIZE);
    printf("Mean: %.2f\n",mean_value);
}

//function that each threads will use to sort their section of the array
void* sort_chunk(void *arg)
{
    thread_chunk *chunk = (thread_chunk*)arg;

    int size = chunk->end - chunk->start + 1;

    qsort(&chunk->data[chunk->start],size,sizeof(int),compare);

    return NULL;
}

//merges 2 sections of the array
void merge(int *data, int start1, int end1, int start2, int end2, int *temp)
{
    int i = start1;
    int j = start2;
    int k = 0;

    while (i <= end1 && j <= end2)
    {
        if (data[i] <= data[j])
            temp[k++] = data[i++];
        else
            temp[k++] = data[j++];
    }

    while (i <= end1)
        temp[k++] = data[i++];

    while (j <= end2)
        temp[k++] = data[j++];

    for (int x = 0; x < k; x++)
        data[start1 + x] = temp[x];
}

//merges all the sections of the array
void merge_all(thread_chunk *chunks, int thread_num, int *data, int SIZE)
{
    int *temp = malloc(SIZE * sizeof(int));

    int left_start = chunks[0].start;
    int left_end   = chunks[0].end;

    for (int i = 1; i < thread_num; i++)
    {
        merge(data, left_start, left_end, chunks[i].start, chunks[i].end, temp);
        left_end = chunks[i].end;
    }

    free(temp);
}

//create X threads, making them sort a section of the array
void sort(int *data, int thread_num, int SIZE)
{
    pthread_t threads[thread_num];
    thread_chunk chunks[thread_num];
    int chunk_size = SIZE / thread_num;

    int start = 0;

    for (int i = 0; i < thread_num; i++)
    {
        chunks[i].data = data;

        chunks[i].start = start;

        int end = start + chunk_size - 1;

        if (i == thread_num - 1)
            end = SIZE - 1;

        chunks[i].end = end;

        start = end + 1;
    }

    // Sort chunks
    for (int i = 0; i < thread_num; i++)
        pthread_create(&threads[i], NULL, sort_chunk, &chunks[i]);

    for (int i = 0; i < thread_num; i++)
        pthread_join(threads[i], NULL);

    // Merge chunks
    merge_all(chunks, thread_num, data, SIZE);
}

//functions for timing
static inline int64_t nsec_from_timespec(const struct timespec *ts) 
{
    return (int64_t)ts->tv_sec * 1000000000LL + (int64_t)ts->tv_nsec;
}
//function for timing
static inline double elapsed_ms(struct timespec t0, struct timespec t1) 
{
    return (nsec_from_timespec(&t1) - nsec_from_timespec(&t0)) / 1e6; // double ms
}

int main(int argc, char *argv[])
{
    struct timespec t0,t1;
    clock_gettime(CLOCK_MONOTONIC, &t0);
    
    //ensures required arguments are met
    if (argc != 3)
    {
        printf("Error - Missing arguments\n");
    }
    
//begins reading
    FILE *read_file = fopen(argv[2], "r");

    //file validation
    if (read_file == NULL)
    {
        printf("File: %s not found\n",argv[2]);
        return 0;
    }
    //manage file header
    int SIZE = read_header(read_file);

    //return validation
    if (MAX_SIZE < SIZE)
    {
        printf("Integer size surpasses limit of this program's scope");
    }
    else if (SIZE == -1)
    {
        printf("ERROR detected while reading header\n");
        return 0;
    }

    int *data = malloc(SIZE * sizeof(int));

    //manage file body
    read_body(read_file,data, SIZE);

    fclose(read_file);
//end of reading

    int thread_num = atoi(argv[1]);

    //sort data
    if (thread_num == 1)
        qsort(data, SIZE, sizeof(int), compare);
    else if (thread_num < 5)
        sort(data,thread_num,SIZE);
    else
        printf("number of threads is too high, out of program's scope");

    //gets median, mode, and average of data
    compute(data, SIZE);
    free(data);

    clock_gettime(CLOCK_MONOTONIC, &t1);
    double ms = elapsed_ms(t0,t1);
    printf("elasped: %.3f ms\n", ms);
}