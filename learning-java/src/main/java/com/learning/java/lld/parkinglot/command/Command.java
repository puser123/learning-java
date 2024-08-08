package com.learning.java.lld.parkinglot.command;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Command Object
 */
public class Command {

    private String name;
    private List<String> arguments;

    public Command(String command) {
        if(!command.isEmpty()) {
            List<String> commandString = Arrays.stream(command.split(" ")).collect(Collectors.toList());
            this.name = commandString.get(0);
            this.arguments =commandString.subList(1, commandString.size());
        }
    }
    public String getName() {
        return name;
    }

    public List<String> getArguments() {
        return arguments;
    }
}
