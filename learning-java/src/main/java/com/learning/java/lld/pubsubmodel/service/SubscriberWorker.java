package com.learning.java.lld.pubsubmodel.service;

import com.learning.java.lld.pubsubmodel.model.Message;
import com.learning.java.lld.pubsubmodel.model.Subscriber;
import com.learning.java.lld.pubsubmodel.model.Topic;
import com.learning.java.lld.pubsubmodel.model.TopicSubscriber;
import lombok.SneakyThrows;

public class SubscriberWorker implements Runnable {

    private final Topic topic;
    private final Subscriber topicSubscriber;

    SubscriberWorker(final Topic topic, final Subscriber topicSubscriber) {
        this.topic = topic;
        this.topicSubscriber = topicSubscriber;
    }

    @SneakyThrows
    @Override
    public void run() {

        synchronized (this.topicSubscriber) {
            do{
                int currentOffSet = topicSubscriber.getOffSet().get();
                while(currentOffSet >= this.topic.getMessageList().size()) {
                    topicSubscriber.wait(); // stops current thread on this topic
                }
                Message message = topic.getMessageList().get(currentOffSet);
                topicSubscriber.consume(message);
                topicSubscriber.getOffSet().compareAndSet(currentOffSet, currentOffSet + 1);
            } while (true);
        }
    }

    synchronized  public void wakeUpThread() {
        synchronized (topicSubscriber) {
            topicSubscriber.notify();
        }
    }
}
