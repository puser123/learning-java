package com.learning.java.lld.pubsubmodel.service;

import com.learning.java.lld.pubsubmodel.model.Subscriber;
import com.learning.java.lld.pubsubmodel.model.Topic;
import com.learning.java.lld.pubsubmodel.model.TopicSubscriber;

import java.util.HashMap;
import java.util.Map;

/**
 * Manages publishing logic and call all subscriber to start Consuming
 */
public class TopicHandler {

    private final Topic topic;
    private final Map<String, SubscriberWorker> subscriberWorkerMap;

    public TopicHandler(final Topic topic) {
        this.topic = topic;
        this.subscriberWorkerMap = new HashMap<>();
    }


    public void startSubscribers() {
        for(Subscriber subscriber: topic.getSubscribers()) {
            this.startSubscriber(subscriber);
        }
    }

    private void startSubscriber(Subscriber subscriber) {
        // Create subscriber worker if not present
        final String subscriberId =subscriber.getId();
        if(!subscriberWorkerMap.containsKey(subscriberId)) {
            subscriberWorkerMap.put(subscriberId, new SubscriberWorker(this.topic, subscriber));
            new Thread(subscriberWorkerMap.get(subscriberId)).start();
        }
        SubscriberWorker subscriberWorker = subscriberWorkerMap.get(subscriberId);
        subscriberWorker.wakeUpThread();
    }

}
