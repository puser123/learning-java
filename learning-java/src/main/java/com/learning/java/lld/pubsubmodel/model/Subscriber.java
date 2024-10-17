package com.learning.java.lld.pubsubmodel.model;

import java.util.concurrent.atomic.AtomicInteger;

public interface Subscriber {

     String getName();
     String getId();
     AtomicInteger getOffSet();
     void consume(Message message);
}
