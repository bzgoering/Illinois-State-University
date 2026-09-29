package Threads;

import java.lang.Thread;

public class Thread_Program implements Runnable
{
    @Override
    public void run()
    {
        System.out.println("Ex2: Hello from " + Thread.currentThread().getName());
    }

    public static void main(String[] args) throws InterruptedException
    {
        if (args.length != 1)
        {
            System.out.println("Expected parameter input: 1\nActual: " + args.length);
            return;
        }

        Runnable obj = new Thread_Program();
        int count = Integer.parseInt(args[0]);
        Thread[] threads = new Thread[count];

        for (int x = 0; x < count; x++)
        {
            threads[x] = new Thread(obj);
            threads[x].start();
        }

        for (int x = 0; x < count; x++)
        {
            threads[x].join();
        }
    }
}