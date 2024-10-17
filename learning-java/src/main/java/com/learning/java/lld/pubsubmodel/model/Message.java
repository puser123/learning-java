package com.learning.java.lld.pubsubmodel.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Message {
    private String value;

    public Message(String value) {
        this.value = value;
    }
}
