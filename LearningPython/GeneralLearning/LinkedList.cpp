//swaps the two nodes following prevNode
void swap(Node* prevNode)
{
    //1st element gets put into temp slot and skips the next element
    Node* temp = new Node(prevNode->next->dataVal);
    temp->next = prevNode->next->next->next;
    
    //1st element is replaced by skiped element
    prevNode->next = prevNode->next->next;
    //instert temp node
    prevNode->next->next = temp;
}

void swap(DLNode* prevNode)
{
    //1st elemet gets put in temp slot and skips the next element whie revers pointing to the 2nd element
    DLNode* temp = new DLNode(prevNode->next->dataVal);
    temp->next = prevNode->next->next->next;
    temp->prev = prevNode->next->next;
    //4th element points to temp
    prevNode->next->next->next->prev = temp;

    //2nd element takes 1st element spot
    prevNode->next = prevNode->next->next;
    //2nd element now points to head
    prevNode->next->prev = prevNode;
    //insert 1st element
    prevNode->next->next = temp;
}

// prints the elements from listToPrintFrom at the indices in indexList to the screen. 
// The indices are zero-based. Neither list is modified in any way.
// Preconditions: indexList is sorted in ascending order and the last value in
//                indexList is less than the length of listToPrintFrom
//Big-Oh:O(n)
void printByIndex(Node* listToPrintFrom, Node* indexList)
{
    cout<<" ";

    //make temp lists
    Node* tempIndex = indexList;
    Node* tempList = listToPrintFrom;

    int index = tempIndex->dataVal;

    for (int x = 0; tempIndex != nullptr; x++)
    {   
        if (index == x)
        {
            cout<<tempList->dataVal<<" ";
            if (tempIndex->next != nullptr)
                index = tempIndex->next->dataVal;
            tempIndex=tempIndex->next;
        }
        
        tempList = tempList->next;
    }
    cout<<endl;
}

void printList(Node* theList)
{
    cout << "The list:";
    Node* curPtr = theList;
    while (curPtr)
    {
        cout << " " << curPtr->dataVal;
        curPtr = curPtr->next;
    }
    cout << endl;
}

void printList(DLNode* theList)
{
    DLNode* curPtr = theList;
    DLNode* prevPtr = nullptr;
    cout << "The list forward:";
    while (curPtr)
    {
        cout << " " << curPtr->dataVal;
        prevPtr = curPtr;
        curPtr = curPtr->next;
    }
    cout << "\nThe list backward:";
    while (prevPtr)
    {
        cout << " " << prevPtr->dataVal;
        prevPtr = prevPtr->prev;
    }
    cout << endl;
}

Node* createList(int elements[], int numElements)
{
    Node* head = new Node(elements[0]); // create the first node
    Node* curNode = head;
    for (int i = 1; i < numElements; i++)
    {
        Node* theNode = new Node(elements[i]); // create the node
        curNode->next = theNode; // add it to the end of the list
        curNode = theNode; // make curNode point to it.
    }
    return head;
}

DLNode* createDLList(int elements[], int numElements)
{
    DLNode* head = new DLNode(elements[0]); // create the first node
    DLNode* curNode = head;
    for (int i = 1; i < numElements; i++)
    {
        DLNode* theNode = new DLNode(elements[i], curNode); // create the node -- pointing at previous node
        curNode->next = theNode; // add it to the end of the list
        curNode = theNode; // make curNode point to it.
    }
    return head;
}
