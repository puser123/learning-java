package com.learning.java.lld.parkinglot.command;

import ch.qos.logback.core.util.StringUtil;
import com.learning.java.lld.parkinglot.model.Vehicle;
import com.learning.java.lld.parkinglot.model.VehicleFactory;
import com.learning.java.lld.parkinglot.service.ParkingService;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.Locale;

@Slf4j
public class ParkCommandExecutor extends CommandExecutor{

    public static String commandName = "park";
    private VehicleFactory vehicleFactory;
    public ParkCommandExecutor(ParkingService parkingService, VehicleFactory vehicleFactory) {
        super(parkingService);
        this.vehicleFactory = vehicleFactory;
    }

    @Override
    protected boolean validateCommand(Command command) {
        if(!commandName.equals(command.getName().toLowerCase())
                || command.getArguments().size() < 1
                || !Arrays.asList("car", "bike").contains(command.getArguments().get(0).toLowerCase(Locale.ROOT))
                || !command.getArguments().get(1).matches("[0-9a-zA-Z]+")
                || !command.getArguments().get(2).matches("[a-zA-Z]+")) {
            return false;
        }

        if(!parkingService.isReady()) {
            return false;
        }
        return true;
    }

    @Override
    public void execute(Command command) {
        String vehicleType = command.getArguments().get(0);
        String vehicleNumber = command.getArguments().get(1);
        String color = command.getArguments().get(2);
        Vehicle vehicle = vehicleFactory.getVehicle(vehicleType, vehicleNumber, color);
        try {
            this.parkingService.park(vehicle);
        } catch (Exception e) {
            log.info("Unable to park the vehicle");
        }
    }
}
