package com.learning.java.lld.pubsubmodel.model;

import java.util.concurrent.atomic.AtomicInteger;

public class TopicSubscriber implements Subscriber{

    private String name;
    private String id;
    private AtomicInteger offSet;

    public TopicSubscriber(String name) {
        this.name = name;
        this.id = name;
        // Initially it will be zero.
        this.offSet = new AtomicInteger(0);
    }
    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void consume(Message message) {
        System.out.println("Message consumed by subscriber : " + this.name + " Message : " + message.getValue());
    }

    public String getId() {
        return id;
    }

    @Override
    public AtomicInteger getOffSet() {
        return this.offSet;
    }
}
