#ifndef MATRIX_H
#define MATRIX_H
#include <iostream>
#include <string>
using std::string;
using namespace std;

class Matrix
{
    public:
        int myArray[3][3];
        int size;
        Matrix();
        string toString();

        //operator overloading
        bool operator==(const Matrix& obj) const;
        Matrix operator* (const Matrix& obj) const;      
};
#endif