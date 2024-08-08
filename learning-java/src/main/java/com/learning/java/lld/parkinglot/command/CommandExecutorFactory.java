package com.learning.java.lld.parkinglot.command;

import com.learning.java.lld.parkinglot.model.VehicleFactory;
import com.learning.java.lld.parkinglot.service.ParkingService;

import java.util.HashMap;
import java.util.Map;

public class CommandExecutorFactory {


    private Map<String, CommandExecutor> commandMap = new HashMap<>();

    public CommandExecutorFactory(ParkingService parkingService, VehicleFactory vehicleFactory) {
        commandMap.put(CreateParkingLotCommandExecutor.commandName,
                new CreateParkingLotCommandExecutor(parkingService));
        commandMap.put(ParkCommandExecutor.commandName,
                new ParkCommandExecutor(parkingService, vehicleFactory));
        commandMap.put(UnParkCommandExecutor.commandName,
                new UnParkCommandExecutor(parkingService));
        commandMap.put(StatusCommandExecutor.commandName,
                new StatusCommandExecutor(parkingService));

    }

    public CommandExecutor getCommandExecutor(Command command) {
        return commandMap.get(command.getName().toLowerCase());
    }

}
