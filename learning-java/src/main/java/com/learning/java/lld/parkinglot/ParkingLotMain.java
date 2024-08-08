package com.learning.java.lld.parkinglot;

import com.learning.java.lld.parkinglot.command.Command;
import com.learning.java.lld.parkinglot.command.CommandExecutorFactory;
import com.learning.java.lld.parkinglot.model.VehicleFactory;
import com.learning.java.lld.parkinglot.service.ParkingService;
import com.learning.java.lld.parkinglot.service.ParkingServiceImpl;

/**
 * Client which calls various command to execute.
 */
public class ParkingLotMain {

    public static void main(String[] args) throws Exception {
        ParkingService parkingService = new ParkingServiceImpl();
        CommandExecutorFactory commandExecutorFactory = new CommandExecutorFactory(parkingService, new VehicleFactory());

        Command command1 = new Command("create_parking_lot 10");
        commandExecutorFactory.getCommandExecutor(command1)
                .validateAndExecuteCommand(command1);
        Command command2 = new Command("park car 1234 RED");
        commandExecutorFactory.getCommandExecutor(command2)
                .validateAndExecuteCommand(command2);
        Command command3 = new Command("park bike 12 RED");
        commandExecutorFactory.getCommandExecutor(command3)
                .validateAndExecuteCommand(command3);
        Command command4 = new Command("status ");
        commandExecutorFactory.getCommandExecutor(command4)
                .validateAndExecuteCommand(command4);

    }
}
