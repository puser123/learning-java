package com.learning.java.lld.pubsubmodel.service;

import com.learning.java.lld.pubsubmodel.model.Message;
import com.learning.java.lld.pubsubmodel.model.Subscriber;
import com.learning.java.lld.pubsubmodel.model.Topic;

import java.util.HashMap;
import java.util.Map;

public class Broker {

    private Map<String, TopicHandler> topicHandlerMap;

    public Broker() {
        this.topicHandlerMap = new HashMap<>();
    }

    public Topic createTopic(String topicName) {
        final Topic topic = new Topic(topicName, topicName);
        TopicHandler topicHandler = new TopicHandler(topic);
        this.topicHandlerMap.put(topic.getTopicId(), topicHandler);
        return topic;
    }

    public void addSubscriber(Topic topic, Subscriber subscriber) {
        topic.addSubscriber(subscriber);
        System.out.println("Subscribed added for topic : " + topic.getTopicName() + " Subscriber: " + subscriber.getName());
    }

    public void publish(Message message, Topic topic) {

        System.out.println("Published message : " + message.getValue());
        topic.publishMessage(message);
        // We need to notify topic handler to start publishing
        new Thread(() -> this.topicHandlerMap.get(topic.getTopicId()).startSubscribers()).start();
        System.out.println("Started Subscribers");
    }

}
