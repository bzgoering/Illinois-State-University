#ifndef STUDENT_H
#define STUDENT_H
#include <iostream>
using std::string;

class Student
{   
    //private variables
    string studentId;
    string address;

    public:
        //public varables
        string email;
        string name;
        double grade;

        //constructor
        Student(string id);

        //methods
        void addAddress(string sn, int hn, string an, string ct, string st, string cou);
        void display() const;
};

#endif