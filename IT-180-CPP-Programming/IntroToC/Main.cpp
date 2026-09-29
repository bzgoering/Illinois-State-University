#include<iostream>
using namespace std;

//finds highest integer in array
int find_Highest(int array[], int size)
{
    int currentHigh = array[0];

    for(int x = 1; x < size; x++)
    {
        if (currentHigh < array[x])
        {
            currentHigh = array[x];
        }
    }

    return currentHigh;
}

//finds lowest integer in array
int find_Lowest(int array[], int size)
{
    int currentLow = array[0];

    for(int x = 1; x < size; x++)
    {
        if (currentLow > array[x])
        {
            currentLow = array[x];
        }
    }

    return currentLow;
}

//calculates mean in array
double mean(int array[], int size)
{
    double total = 0.0;
    
    for(int x = 0; x < size; x++)
    {
        total = total + array[x];
    }

    total = total/size;
    return total;
}

//ask for user input to add to array
void add(int array[], int size)
{
    for(int x = 0; x < 15; x++)
    {
        cout<<"Enter a Number:["<<x<<"]: ";
        cin>>array[x];
    }
}

//prints all elements of the array
void print(int array[], int size)
{
    for(int x = 0; x < size; x++)
    {
        cout<<x+1<<": ";
        cout<<array[x];
        cout<<endl;
    }
}

int main()
{
    //integer array with a size 15
    int size = 15;
    int array[15];

    //adds user input to array
    add(array, size);
    cout<<endl;

    //prints the mean
    cout<<"Mean: ";
    cout<<mean(array,size);
    cout<<endl;

    //prints the lowest integer in array
    cout<<"Lowest: ";
    cout<<find_Lowest(array,size);
    cout<<endl;

    //prints the highest integer in array
    cout<<"Highest: ";
    cout<<find_Highest(array,size);
    cout<<endl;
    
    //prints all elements of the array
    cout<<"Elements of array: "<<endl;
    print(array,size);
}