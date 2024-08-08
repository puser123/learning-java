package com.learning.java.lld.parkinglot.command;

import com.learning.java.lld.parkinglot.service.ParkingService;

import java.util.regex.Pattern;

public class CreateParkingLotCommandExecutor extends CommandExecutor{

    public static String commandName = "create_parking_lot";

    public CreateParkingLotCommandExecutor(ParkingService parkingService) {
        super(parkingService);
    }

    @Override
    protected boolean validateCommand(Command command) {
        if(!commandName.equals(command.getName()) ||
                command.getArguments().size() < 1 ||
        !command.getArguments().get(0).matches("[0-9]+")) {
            return false;
        }
        return true;
    }

    @Override
    public void execute(Command command) {
        int capacity = Integer.valueOf(command.getArguments().get(0));
        this.parkingService.createParkingLot(capacity);

    }
}
