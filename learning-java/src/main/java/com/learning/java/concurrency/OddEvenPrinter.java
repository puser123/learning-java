package com.learning.java.concurrency;

import java.util.concurrent.Semaphore;

/*
    There is simple odd even printer, where one thread is printing odd and other thread is printing even number,
     1. using binary semaphore.
     2. Using Object wait and Object notify.


 */
class EvenPrinter implements Runnable {

    private volatile int start = 2;
    private Semaphore odd;
    private Semaphore even;

    EvenPrinter(Semaphore oddSemaphore, Semaphore evenSemaphore) {
        this.odd = oddSemaphore;
        this.even = evenSemaphore;
    }

    @Override
    public void run() {
        for(int i=0;i<100;i++) {
            try {
                even.acquire();
                System.out.println("(" + start + ":" + Thread.currentThread().getName() + ")");
                start+=2;
                odd.release();
            } catch (InterruptedException e) {
               System.out.println("Exception occurred");
            }
        }
    }
}

class OddPrinter implements Runnable {

    private volatile int start = 1;
    private Semaphore odd;
    private Semaphore even;

    OddPrinter(Semaphore oddSemaphore, Semaphore evenSemaphore) {
        this.odd = oddSemaphore;
        this.even = evenSemaphore;
    }

    @Override
    public void run() {
        for(int i=0;i<100;i++)  {
            try {
                odd.acquire();
                System.out.println("(" + start + ":" + Thread.currentThread().getName() + ")");
                start+=2;
                even.release();
            } catch (InterruptedException e) {
                System.out.println("Exception occurred");
            }
        }
    }
}


public class OddEvenPrinter {

    public static void main(String[] args) {
        Semaphore odd = new Semaphore(1);
        Semaphore even = new Semaphore(0);
        Thread evenPrinter = new Thread(new EvenPrinter(odd, even));
        Thread oddPrinter = new Thread(new OddPrinter(odd, even));
        evenPrinter.start();
        oddPrinter.start();
    }
}
