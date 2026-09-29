#include <iostream>
#include "ArrayListClass.h"
using namespace std;

int main()
{   
    //variables
    ArrayList list;

    //shows initial list
    cout<<"list elements: "; 
    list.toString();
    cout<<endl;
   
    //add some content
    cout<<"adding some numbers..."<<endl;
    for (int x = 1; x < 11; x++)
    {
        list.push(x);
    }
    cout<<"list elements: ";
    list.toString();
    cout<<endl;

    //removing elements
    cout<<"removing some numbers..."<<endl;
    list.erase(0);
    list.erase(5);
    list.erase(10);
    list.erase(8);
    cout<<"list elements: ";
    list.toString();
    cout<<endl;

    //removing all elements randomly
    cout<<"removing all numbers..."<<endl;
    list.erase(1);
    list.erase(4);
    list.erase(7);
    list.erase(6);
    list.erase(9);
    list.erase(2);
    list.erase(3);
    list.erase(9); //testing no number found
    cout<<"list elements: ";
    list.toString();
    cout<<endl;
    
    list.push(0);
    cout<<"list elements: ";
    list.toString();
    cout<<endl;
    }