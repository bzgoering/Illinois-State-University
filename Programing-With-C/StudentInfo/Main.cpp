#include "StudentClass.h"

int main()
{
    //creates student
    Student rishi("rasaripa");
    //add student data
    rishi.email = "rishi.saripalle@ilstu.edu";
    rishi.name = "Rishi Saripalle";
    rishi.grade = 3.8;
    rishi.addAddress("Pennsylvania Avanue NW", 1600, "Presidential Suites", "Washinton", "D.C.", "USA");
    //print data to screen
    rishi.display();
}