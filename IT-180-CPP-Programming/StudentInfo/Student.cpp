#include <iostream>
#include "StudentClass.h"
using namespace std;

//creates student with id
Student::Student(string id)
{
    studentId = id;
}

//adds address of student and puts into proper format
void Student::addAddress(string sn, int hn, string an, string ct, string st, string cou)
{
    string num = to_string(hn);
    address = num + " " + sn + ", " + an + ", " + ct + ", " + st + ", " + cou;
}

//displays the students id, email, gpa, and address
void Student::display() const
{
    cout<<name<<" - has student id: "<<studentId<<", email: "<<email<<", and final GPA of: "<<grade<<endl;
    cout<<address<<endl;
}