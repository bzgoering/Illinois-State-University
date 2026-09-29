void doUnion(const list<int>& list1, const list<int>& list2, list<int>& result)
{
    //creates iterators to travse the list
    list<int>::const_iterator iter1 = list1.begin();
    list<int>::const_iterator iter2 = list2.begin();
   
    //adds list 1 and 2 too results sorted
    while (iter1 != list1.end() && iter2 != list2.end())
    {
        //adds list 1 element since its smaller
        if (*iter1 < *iter2)
        {
            result.push_back(*iter1);
            iter1++;
        }
        //adds list 2 element since its smaller
        else if (*iter2 < *iter1)
        {
            result.push_back(*iter2);
            iter2++;
        }
        //they are the same
        else if (*iter1 == *iter2)
        {
            result.push_back(*iter1);
            iter1++;
            iter2++;
        }
    }
    
    //adds the rest from the list
    if (iter1 != list1.end())
    {
        while (iter1 != list1.end())
        {
            result.push_back(*iter1);
            iter1++;
        }
    }

    if (iter2 != list2.end())
    {
        while (iter2 != list2.end())
        {
            result.push_back(*iter2);
            iter2++;
        }
    }
}

void doIntersection(const list<int>& list1, const list<int>& list2, list<int>& result)
{
    //creates iterators to safley travser list
    list<int>::const_iterator iter1 = list1.begin();
    list<int>::const_iterator iter2 = list2.begin();

    //adds fromn list 1 and 2 if they are equal
    while (iter1 != list1.end() && iter2 != list2.end())
    {   
        //skips list1 if its smaller
        if (*iter1 < *iter2)
        {
            iter1++;
        }
        //skips list2 if its smaller
        else if (*iter2 < *iter1)
        {
            iter2++;
        }
        //adds since they are equal
        else if (*iter1 == *iter2)
        {
            result.push_back(*iter1);
            iter1++;
            iter2++;
        }
    }
}

void printList(const list<int>& theList)
{
    list<int>::const_iterator iter;
    for (iter = theList.begin(); iter != theList.end(); iter++)
    {
        cout << " " << *iter;
    }
    cout << endl;

}
