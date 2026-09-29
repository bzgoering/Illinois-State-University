#ifndef ArrayList_H
#define ArrayList_H
#include <iostream>
using std::string;

class ArrayList{
    //private variables
    int* array; //space
    int size; //size of used space
    int totalSize; // total size of space
public:
    ArrayList(); //default constructor
    void push(int m); //adds to end of the array, doubles the size if full
    void erase(int m); //removes from front of array
    void toString(); //list array elements
private:
    void doubleArray(); //expands the total size of the array
    void halfArray(); //halves the total size of the array
    void copy(int start, int a[]);//copies from this.array to another a array
};
#endif