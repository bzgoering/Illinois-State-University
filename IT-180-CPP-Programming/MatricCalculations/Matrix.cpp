#include <iostream>
#include "matrix_head.h"
#include <random>
using namespace std;

int rowSize = 3;
int columnSize = 3;

Matrix::Matrix()
{    
    for(int x = 0; x < rowSize; x++)
    {
        for (int y = 0; y < columnSize; y++)
        {
            int randomNum = rand() % 51;
            myArray[x][y] = randomNum;
        }
    }
}

Matrix Matrix::operator*(const Matrix& obj) const
{
    Matrix result;

    for (int x = 0; x < rowSize; x++)
    {
        for (int y = 0; y<columnSize; y++)
        {
            result.myArray[x][y] = 0;
            for (int z = 0; z < 3; z++)
            {
                result.myArray[x][y] += myArray[x][z] * obj.myArray[z][y];
            }
        }
        
    }

    return result;
}

bool Matrix::operator==(const Matrix& obj) const
{
    bool result = true;

    for (int x = 0; x < rowSize; x++)
    {
        for (int y = 0; y<columnSize; y++)
        {
            if (myArray[x][y] != obj.myArray[x][y])
            {
                return false;
            }
        }
    }

    return result;
}

string Matrix::toString()
{
    string result = "";

    for (int x = 0; x < 3; x++)
    {
        for (int y = 0; y<3; y++)
        {
            result += "[" + to_string(myArray[x][y]) + "] ";
        }

        result += "\n";
    }

    return result;
}