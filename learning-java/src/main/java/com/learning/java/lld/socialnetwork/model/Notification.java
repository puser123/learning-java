package com.learning.java.lld.socialnetwork.model;

import com.learning.java.lld.socialnetwork.model.enums.NotificationType;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class Notification {
    private String id;
    private String byUserId;
    private String toUserId;
    private String description;
    private NotificationType type;

    public String getDescription() {
        return description;
    }

    public String getId() {
        return id;
    }

    public String getByUserId() {
        return byUserId;
    }

    public NotificationType getType() {
        return type;
    }

    public String getToUserId() {
        return toUserId;
    }
}
