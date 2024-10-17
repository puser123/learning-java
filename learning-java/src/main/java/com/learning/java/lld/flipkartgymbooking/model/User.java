package com.learning.java.lld.flipkartgymbooking.model;

import lombok.Data;
import org.apache.commons.lang3.tuple.ImmutablePair;

import java.time.LocalDate;
import java.util.Map;
import java.util.UUID;

@Data
public class User {
    private UUID userId;
    private String userName;
    private String email;
    private String password;
}
