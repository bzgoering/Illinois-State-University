#include <iostream>
#include "ArrayListClass.h"
using namespace std;

    //defualt constructor
    ArrayList::ArrayList()
    {
        array[0] = 0;
        size = 1;
        totalSize = 1;
        
        cout<<"list constructed"<<endl;
    }

    //adds to list
    void ArrayList::push(int m)
    {   
        //ensure theres space for adding
        if (size == totalSize)
        {
            //makes space if needed
            doubleArray();
            cout<<"list doubled: "<<totalSize/2<< " -> " <<totalSize<<endl;
        }

        //adds element m to end and adds to size
        array[size] = m;
        size++;
        cout<<"list pushed: "<<m<<endl;
    }

    //removes element m
    void ArrayList::erase(int m)
    {
        bool erase = false;

        //searches for element
        for (int x = 0; x < size; x++)
        {
            //find m
            if (array[x] == m)
            {
                //moves elements to the left by one position from m
                for (int y = x; y < size-1; y++)
                {
                    array[y] = array[y+1];
                }
                //updates variables
                size--;
                cout<<"list erased: "<<m<<endl;
                erase = true;
                break;
            }
        }

        if (!erase)
        {
            cout<<"element: "<< m <<" is not in list"<<endl;
        }

        //halfs the array actual size if theres 50% or more of unused space
        if (size <= totalSize/2 && totalSize != 1)
        {
            halfArray();
            cout<<"list has been halved: "<<totalSize*2<<" -> "<<totalSize<<endl;
        }

        return;
    }

    //makes room in array
    void ArrayList::doubleArray()
    {
        //doubles the array actual size
        int* newArray = new int[totalSize*2];

        //copies orginal into new
        copy(0,newArray);

        //update variables
        array = newArray;
        totalSize = totalSize*2;
        return;
    }

    //contract list
    void ArrayList::halfArray()
    {
        //halves the new array
        int* newArray = new int[totalSize/2];

        //copies orginal into new
        copy(0,newArray);

        //update variables
        array = newArray;
        totalSize = totalSize/2;
        return;
    }

    //prints list
    void ArrayList::toString()
    {
        for (int x = 0; x < size; x++)
        {
            cout<< "[" << array[x] <<"]";
        }
        cout<<endl;
        return;
    }

    //copies elements from start to input array from main array
    void ArrayList::copy(int start,int a[])
    {
        int count = 0;
        for (int x = start; x < size; x++)
        {
            a[count] = array[x];
            count++;
        }
        return;
    }
    