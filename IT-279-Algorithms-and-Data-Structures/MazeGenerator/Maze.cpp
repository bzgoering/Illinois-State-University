// Implementation of Maze class
// By Mary Elaine Califf and Ben Goering

#include "Maze.h"
#include "DisjointSet.h"
using namespace std;

Maze::Maze(int rows, int cols, bool stop)
{
    stopEarly = stop;
    cout<<stopEarly<<endl;
    numRows = rows;
    numColumns = cols;
    int numCells = rows * cols;
    mazeWalls = new CellWalls[numCells];
    mazeWalls[numCells - 1].east = false;
}

Maze &Maze::operator=(const Maze &rhs)
{
    if (this != &rhs)
    {
        delete [] mazeWalls;
        this->copy(rhs);
    }
    return *this;
}

void Maze::generateMaze()
{
    int numCells = numRows * numColumns;
    DisjointSet mySet(numCells);
    bool mazeComplete = false;

    // to do -- see assignment instructions for details
    while (!mazeComplete)
    {
        //gets random numbers for maze
        int randIndex = rand() % numCells; //gets maze location:0 to numCells-1
        int randDir = rand() % 4; //gets direction value

        //validate direction
        if (randDir == 1 || randDir == 3)
        {
            int row = randIndex / numColumns;

            if (row == numRows-1) //can't go down
                randDir = 3;
            if (row == 0) //can't go up
                randDir = 1;
        }
        if (randDir == 0 || randDir == 2)
        {
            int col = randIndex % numColumns;

            if (col == numColumns-1) //can't go right
                randDir = 0;
            if (col == 0)
                randDir = 2; //can't go left
        }   

        //gets new location
        int neighbor = randIndex;
        switch (randDir)
        {
            case 0: //left
                neighbor -= 1;
                break;
            case 1: //down
                neighbor += numColumns;
                break;
            case 2: //right
                neighbor += 1;
                break;
            case 3: //up
                neighbor -= numColumns;
                break;
        }

        //tried to break wall
        if (mySet.doUnion(randIndex,neighbor))
        {
            switch(randDir)
            {
                case 0: //left
                    mazeWalls[neighbor].east = false;
                    break;
                case 1: //down
                    mazeWalls[randIndex].south = false;
                    break;
                case 2: //right
                    mazeWalls[randIndex].east = false;
                    break;
                case 3: //up
                    mazeWalls[neighbor].south = false;
                    break;
            }
        }
        //early stop - if end connect to start
        if (mySet.find(0) == mySet.find(numCells-1) && stopEarly)
            mazeComplete = true;
        else
        {
            //regular stoping procedure
            mazeComplete = true; //assumes maze is complete
            int numRoot = 0;
            for (int x = 0; x < numCells; x++)
            {
                if (mySet.find(x) == x)
                    numRoot++;
                if (numRoot > 1)
                {
                    mazeComplete = false;
                    break;
                }
            }
        }
    }
}

void Maze::print(ostream &outputStream)
{
    // print the top row of walls
    for (int i = 0; i < numColumns; i++)
        outputStream << " _";
    outputStream << '\n';
    for (int i = 0; i < numRows; i++)
    {
        int cellbase = i * numColumns;
        // print west wall (except at entrance)
        if (i == 0)
            outputStream << ' ';
        else
            outputStream << '|';
        for (int j = 0; j < numColumns; j++)
        {
            if (mazeWalls[cellbase + j].south)
                outputStream << '_';
            else
                outputStream << ' ';
            if (mazeWalls[cellbase + j].east)
                outputStream << '|';
            else
                outputStream << ' ';
        }
        outputStream << '\n';
    }
}

void Maze::copy(const Maze &orig)
{
    this->numRows = orig.numRows;
    this->numColumns = orig.numColumns;
    int numCells = numRows * numColumns;
    mazeWalls = new CellWalls[numCells];
    for (int i = 0; i < numCells; i++)
    {
        this->mazeWalls[i] = orig.mazeWalls[i];
    }
}