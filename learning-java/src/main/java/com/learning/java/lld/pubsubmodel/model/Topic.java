package com.learning.java.lld.pubsubmodel.model;

import java.util.ArrayList;
import java.util.List;

public class Topic {
    private String topicName;
    private String topicId;
    private List<Message> messageList;
    private List<Subscriber> subscribers;

    public Topic(String topicName, String topicId) {
        this.topicName = topicName;
        this.subscribers = new ArrayList<>(10);
        this.messageList = new ArrayList<>();
        this.topicId = topicId;
    }

    public void addSubscriber(Subscriber subscriber) {
        this.subscribers.add(subscriber);
    }

    public void publishMessage(Message message) {
        this.messageList.add(message);
    }

    public List<Message> getMessageList() {
        return messageList;
    }

    public List<Subscriber> getSubscribers() {
        return subscribers;
    }

    public String getTopicName() {
        return topicName;
    }

    public String getTopicId() {
        return topicId;
    }
}
