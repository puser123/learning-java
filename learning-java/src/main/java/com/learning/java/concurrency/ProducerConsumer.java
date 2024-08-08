package com.learning.java.concurrency;


import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;


import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;

@Slf4j
class Producer implements Runnable {
    private BlockingQueue<String> blockingQueue;

    Producer(BlockingQueue<String> b) {
        blockingQueue = b;
    }

    @SneakyThrows
    @Override
    public void run() {
        // Infinte producer
        while (true){
            String s = RandomStringUtils.randomAlphanumeric(10 );
            log.info("Producing String: {}", s);
            blockingQueue.put(s);
        }
    }
};

@Slf4j
class Consumer implements Runnable {
    private BlockingQueue<String> blockingQueue;

    Consumer(BlockingQueue<String> b) {
        blockingQueue = b;
    }

    @SneakyThrows
    @Override
    public void run() {
        // Infinte producer
        log.info("consumer started");
        while(true) {
            String consumed = blockingQueue.take();
            log.info("Consuming String: {}", consumed);
        }
    }
};



@Slf4j
public class ProducerConsumer {
    public static void main(String[] args) {
        BlockingQueue<String> blockingQueue = new LinkedBlockingDeque<>(100);
        ExecutorService executorServiceProducer = Executors.newFixedThreadPool(10);
        ExecutorService executorServiceConsumer = Executors.newFixedThreadPool(10);

        for(int i=0;i<10;i++) {
            executorServiceProducer.submit(new Producer(blockingQueue));
            executorServiceProducer.submit(new Consumer(blockingQueue));
        }
        executorServiceConsumer.shutdown();
        executorServiceProducer.shutdown();
    }
}
