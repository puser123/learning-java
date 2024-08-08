package com.learning.java.concurrency;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

/**
 * There are many ways to created a synchronised counter in java, there are multiple concurrency options avaiable
 *
 * 1. Using atomic variable
 * 2. Using synchronized block - not discussed here.
 * 3. Using lock from java lock package
 * 4. Using semaphore
 */
class CounterAtomicVariable  implements Runnable{
    private AtomicInteger counter;
    private Integer limit = 3000;

    public CounterAtomicVariable() {
        this.counter =  new AtomicInteger(0);
    }
    @Override
    public void run() {
        while(counter.get() < limit) {
            System.out.println("Counter value: "+  counter.getAndIncrement() + "Thread name: " + Thread.currentThread().getName());
        }
    }

};
class Data {
    private int value = 0;
    private int limit = 4000;
    public Data() {
    }

    public int getValue() {
        return value;
    }
    public int getLimit() {
        return limit;
    }
    public void incrementValue() {value++;};
};

class CounterLock implements Runnable {
    private ReentrantLock lock;
    private volatile Data data;

    public CounterLock(Data d, ReentrantLock loc) {
       data =d;
       lock = loc;
    }
    @Override
    public void run() {
//        lock.lock();
//        while(data.getValue() < data.getLimit()) {
//
//            System.out.println("Counter value: "+  data.getValue() + "Thread name: " + Thread.currentThread().getName());
//            data.incrementValue();
//
//        }
//        lock.unlock();

        // With below the data.value is going more than limit because data.getValue() is not locked and any thrad comes and check
        // if data.getValue is less they are waiting on the lock after while loop check which is causing issue the counter to be more than limit
        while(data.getValue() < data.getLimit()) {
            lock.lock();
            System.out.println("Counter value: "+  data.getValue() + "Thread name: " + Thread.currentThread().getName());
            data.incrementValue();
            lock.unlock();
        }
    }
};


/**
 * Counter semaphore permits threads based on count
 * Counter semaphore limits number of concurrent thread accessing the resource
 * There is timed semaphore which allows number of thread to acquire lock based on time limit
 * If within time limit it will allow only n number of thread to acquire lock. once time limit exhaust - all lock are released
 * Timed semaphore is part of apache-common-lang3
 */
class CounterSemaphore implements Runnable {
    private Semaphore semaphore;
    private volatile Data data;

    public CounterSemaphore(Data d, Semaphore loc) {
        data = d;
        semaphore = loc;
    }
    @Override
    public void run() {

        // With below the data.value is going more than limit because data.getValue() is not locked and any thrad comes and check
        // if data.getValue is less they are waiting on the lock after while loop check which is causing issue the counter to be more than limit
        while(data.getValue() < data.getLimit()) {
            try {
                semaphore.acquire();
                System.out.println("Counter value: "+  data.getValue() + "Thread name: " + Thread.currentThread().getName());
                data.incrementValue();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } finally {
                semaphore.release();
            }
        }
    }
}

@Slf4j
public class SynchronizedCounter {

    public static void main(String[] args) throws InterruptedException {

        Data d = new Data();
        log.info("hello");
        ReentrantLock reentrantLock = new ReentrantLock(); // Allowed only one thread to update at one time same as mutex
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        Semaphore semaphore = new Semaphore(5); // 5 thread is permitted same time.
        for(int i=0;i<10;i++) {
           //  executorService.execute(new CounterAtomicVariable());
            //executorService.execute(new CounterLock(d, reentrantLock));
            executorService.execute(new CounterSemaphore(d, semaphore));
            // We can use
            executorService.submit(() -> {
               System.out.println("Direct executer service submit task");
            });
        }

        // In general exectuer service won't be destroyed after work completion, it will stay alive and wait for new work
        // Executer Service
        //https://www.baeldung.com/java-executor-service-tutorial
        executorService.shutdown();
       // executorService.shutdownNow(); // Shutdown immediate
       // executorService.awaitTermination(10, TimeUnit.SECONDS);


    }
}
