// DisjointSet implementation using union by size and path compression
// By Mary Elaine Califf and Ben Goering

#include "DisjointSet.h"
#include <iostream>

DisjointSet::DisjointSet(int numObjects)
{
    theArray.resize(numObjects,-1);
}

//recursive method to find the item -- does path compression on the way out of the recursion
int DisjointSet::find(int objectIndex)
{
    // to do -- see assignment instructions for details
    if (theArray[objectIndex] < 0)
        return objectIndex; // root is found
    else
        return theArray[objectIndex] = find(theArray[objectIndex]);
        //makes objectIndex will be reassign with the data it points to until the root is found

}

bool DisjointSet::doUnion(int objIndex1, int objIndex2)
{
    // to do -- see assignment instructions for details
    int root1 = find(objIndex1);
    int root2 = find(objIndex2);

    //roots point to same place
    if (root1 == root2)
        return false; 

    //root2 union to root1 if root 1 is larger or equal
    if (theArray[root1] <= theArray[root2])
    {
        theArray[root1] += theArray[root2];
        theArray[root2] = root1;
        return true;
    }
    //root1 union to root2 only if root2 is larger
    else
    {
        theArray[root2] += theArray[root1];
        theArray[root1] = root2;
        return true;
    }
}

void DisjointSet::printArrayValues(std::ostream &outputStream)
{
    for (int i = 0; i < numValues; i++)
    {
        outputStream << theArray[i] << " ";
    }
    outputStream << std::endl;
}