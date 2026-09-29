package JavaSorting;

import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ParallelSort{
    private static ArrayList<Integer> data = new ArrayList<>();

    public static void readFile(String fileName)
    {
        try (BufferedReader reader = new BufferedReader(new FileReader (fileName)))
        {
            String line;

            while ((line = reader.readLine()) != null)
            {
                int num = Integer.parseInt(line);
                data.add(num);
            }
        }
        catch(IOException e)
        {
            System.out.println("File does not exist or probelem wrong with file.");
        }

    }

    public static void InsertionSort(ArrayList<Integer> data)
    {
        for (int x = 1; x < data.size(); x++)
        {
            int key = (int) data.get(x);
            int y = x - 1;

            while (y >= 0 && (int) data.get(y) > key)
            {
                data.set(y+1,data.get(y));
                y--;
            }

            data.set(y+1,key);
        }
    }

    public static void checkSort()
    {
        boolean sorted = true;

        for (int i = 1; i < data.size(); i++) 
        {
            if (data.get(i - 1) > data.get(i)) {
                sorted = false;
                break;
            }
        }

        if (sorted)
        {
            System.out.println("True: List is sorted");

            if (data.size() < 20)
            {
                System.out.print("Final Sorted Data ");
                printArray(data);
            }
        }
        else
            System.out.println("False: List is not sorted");
    }
    
    public static void printArray(ArrayList<Integer> data)
    {
        System.out.print("data = [");

        for(int x = 0; x < data.size()-1; x++)
        {
            System.out.print(data.get(x) + ", ");
        }

        System.out.println(data.get(data.size()-1) + "]");
    }

    public static void main(String[] args) throws InterruptedException
    {
        //checks arguments
        if(args.length != 1)
        {
            System.out.println("Input file name missing");
            System.exit(1);
        }

        //read file and prints unsorted version if its less than 20 
        readFile(args[0]);

        if (data.size() < 20)
        {
            System.out.print("Unsorted ");
            printArray(data);
        }

        //parallized work
        long startTime2 = System.currentTimeMillis();

            //splits array into 2
            ArrayList<Integer> leftSplit = new ArrayList<>(data.subList(0,data.size()/2));
            ArrayList<Integer> rightSplit = new ArrayList<>(data.subList(data.size()/2,data.size()));

            //Sorting Thread work
            Sorter obj1 = new Sorter(leftSplit);
            Sorter obj2 = new Sorter(rightSplit);
            Thread thread1 = new Thread(obj1);
            Thread thread2 = new Thread(obj2);
            thread1.start();
            thread2.start();
            thread1.join();
            thread2.join();

            //merging thread work
            Runnable obj3 = new Merger(obj1.getList(), obj2.getList());
            Thread thread3 = new Thread(obj3);
            thread3.start();
            thread3.join();

        long elasped2 = System.currentTimeMillis() - startTime2;

        //sequential work
        InsertionSort(data);
        long elasped1 = System.currentTimeMillis() - startTime2;

        System.out.println("N=" + data.size() + ", Sequential InsertionSort time:" + elasped1 + " ms");
        System.out.println("N=" + data.size() + ", Parallel Elapsed Time:" + elasped2 + " ms");

        checkSort();
    }

    public static class Sorter implements Runnable{
        ArrayList<Integer> splitData;

        public Sorter(ArrayList<Integer> splitData)
        {
            this.splitData = splitData;
        }

        public void run()
        {
            InsertionSort(splitData);
        }

        public ArrayList<Integer> getList(){
            return splitData;
        }
    }

    public static class Merger implements Runnable{
        ArrayList<Integer> leftData;
        ArrayList<Integer> rightData;

        public Merger(ArrayList<Integer> left, ArrayList<Integer> right)
        {
            this.leftData = left;
            this.rightData = right;
        }

        public void run()
        {
            ArrayList<Integer> result = new ArrayList<>();


            while(leftData.size() != 0 && rightData.size() != 0)
            {
                if (leftData.get(0) < rightData.get(0))
                {
                    result.add(leftData.get(0));
                    leftData.remove(0);
                }
                else
                {
                    result.add(rightData.get(0));
                    rightData.remove(0);
                }
            }

            if (leftData.size() > 0)
                result.addAll(leftData);
            if (rightData.size() > 0)
                result.addAll(rightData);

            data = result;
        }
    }
}