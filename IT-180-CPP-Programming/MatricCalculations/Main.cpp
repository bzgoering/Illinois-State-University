#include <iostream>
#include "matrix_head.h"
using namespace std;
int main()
{
    Matrix A;
    Matrix B;

    //shows orginal matrix
    cout<<"Matrix A: "<<endl<< A.toString()<<endl;
    cout<<"Matrix B: "<<endl<< B.toString()<<endl;

    //multiplies togeather
    Matrix Multiply = A*B;
    cout<<"Multiply: "<<endl<<Multiply.toString()<<endl;

    //test equal
    bool same = A == B;
    cout<<"Same: "<<same<<endl;
}