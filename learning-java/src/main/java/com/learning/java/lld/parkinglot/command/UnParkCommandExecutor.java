package com.learning.java.lld.parkinglot.command;

import com.learning.java.lld.parkinglot.exception.SlotValidationException;
import com.learning.java.lld.parkinglot.service.ParkingService;

public class UnParkCommandExecutor extends CommandExecutor{

    public static String commandName = "unpark";

    public UnParkCommandExecutor(ParkingService parkingService) {
        super(parkingService);
    }

    @Override
    protected boolean validateCommand(Command command) {
        if(!commandName.equals(command.getName()) ||
                command.getArguments().size() < 1 ||
                !command.getArguments().get(0).matches("[0-9]+")) {
            return false;
        }
        if(!parkingService.isReady()) {
            return false;
        }
        return true;
    }

    @Override
    public void execute(Command command) throws SlotValidationException {
        int slotNumber = Integer.valueOf(command.getArguments().get(0));
        this.parkingService.unPark(slotNumber);
    }
}
