package com.learning.java.lld.parkinglot.command;

import com.learning.java.lld.parkinglot.service.ParkingService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class CommandExecutor {

    protected ParkingService parkingService;

    public CommandExecutor(ParkingService parkingService) {
        this.parkingService = parkingService;

    }

    protected abstract boolean validateCommand(Command command);

    public  void validateAndExecuteCommand(Command command) throws Exception {
        if(validateCommand(command)) {
            this.execute(command);
        } else {
            log.info("Invalid Command input : {}", command.getName());
        }
    }

    public abstract void execute(Command command) throws Exception;
}
