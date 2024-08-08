package com.learning.java.lld.parkinglot.command;

import com.learning.java.lld.parkinglot.service.ParkingService;

public class StatusCommandExecutor extends CommandExecutor{


    public static final String commandName = "status";

    public StatusCommandExecutor(ParkingService parkingService) {
        super(parkingService);
    }

    @Override
    protected boolean validateCommand(Command command) {
        if(!parkingService.isReady()) {
            return false;
        }
        return commandName.equals(command.getName().toLowerCase());
    }

    @Override
    public void execute(Command command) throws Exception {
        this.parkingService.getParkingStatus();
    }
}
