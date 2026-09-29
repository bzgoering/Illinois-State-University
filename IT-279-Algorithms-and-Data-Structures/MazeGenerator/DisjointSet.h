// Disjoint set class
// By Mary Elaine Califf
#ifndef DISJOINT_SET_H
#define DISJOINT_SET_H

#include <vector>
#include <iostream>

class DisjointSet
{
private:
    std::vector<int> theArray;
    int numValues;

public:
    // constructor
    DisjointSet(int numObjects);

    // find method returns the label for the set the object is in
    // Precondition: objectIndex must be in the range 0 to numValues -1
    int find(int objectIndex);

    // union method joins the sets of object1 and object2
    // Precondition: the objects must NOT be in the same set when union is called
    // Precondition: both parameters must be in the range 0 to numValues-1
    // returns true if all values in the array are now in the same set and false otherwise
    bool doUnion(int objIndex1, int objIndex2);

    // provided for testing purposes
    void printArrayValues(std::ostream& outputStream);
};

#endif