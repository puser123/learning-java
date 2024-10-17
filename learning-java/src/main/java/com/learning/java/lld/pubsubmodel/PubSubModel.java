package com.learning.java.lld.pubsubmodel;

import com.learning.java.lld.pubsubmodel.model.Message;
import com.learning.java.lld.pubsubmodel.model.Subscriber;
import com.learning.java.lld.pubsubmodel.model.Topic;
import com.learning.java.lld.pubsubmodel.model.TopicSubscriber;
import com.learning.java.lld.pubsubmodel.service.Broker;

public class PubSubModel {

    // https://www.youtube.com/watch?v=4BEzgPlLKTo Video by Udit Aggarwal
    //
    // Requirement

    /**
     * Should support multiple topic
     * Subscriber should be able to subscribe to a topic.
     * Publisher should be able to publish to a topic
     * when ever a message gets published to a topic, all subscriber who are subscribing to that topic should be able to consume those messages.
     * Subscriber will be running in parallel.
     * Assummption
     * A subscriber is a single threaded worker.
     *
     *Entities  and Classes
     * Message
     * Topic
     * TopicSubscriber
     * TopicHandler
     * SubscriberHandler
     * Broker
     */

    public static void main(String[] args) {
        Broker broker = new Broker();
        //A broker can contain multiple topics
        Topic cricketTopic = broker.createTopic("cricket");
        Topic footBallTopic = broker.createTopic("football");

        Subscriber cricketScoreSubscriber = new TopicSubscriber("cricketSore");
        Subscriber cricketNewsSubscriber = new TopicSubscriber("cricketNews");


        Subscriber footBallScoreSubscriber = new TopicSubscriber("footBallScore");
        Subscriber footBallNewsSubscriber = new TopicSubscriber("footBallNews");

        broker.addSubscriber(cricketTopic, cricketNewsSubscriber);
        broker.addSubscriber(cricketTopic, cricketScoreSubscriber);


        broker.addSubscriber(footBallTopic, footBallNewsSubscriber);
        broker.addSubscriber(footBallTopic, footBallScoreSubscriber);
        System.out.println("-----------");
        broker.publish(new Message("Dhoni scored 6"), cricketTopic);

    }

}
