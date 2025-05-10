package main;

import command.Command;
import command.DeviceSpecificCommand;
import command.TurnAllOffCommand;
import command.TurnAllOnCommand;
import controlPanel.MainControlPanel;
import devices.SmartDevice;
import factory.*;
import strategy.ActiveMode;
import strategy.AwayMode;
import strategy.PetMode;
import users.Admin;
import users.User;

public class Main {
    public static void main(String[] args) {
        System.out.println("\nWelcome toABCD Smart Home Automation System!");

        // Initialize the control panel
        System.out.println("\n--- Initializing Control Panel ---");
        MainControlPanel panel = MainControlPanel.getInstance();
        System.out.println("Control Panel initialized");
        
        User user1 = new User("Alice", false); // will be admin
        User user2 = new User("Bob", false); // user (non-child)
        User user3 = new User("Charlie", true); // user (child)

        // Register Users (Observer pattern)
        Admin.setInstance(user1);

        panel.register(user1);
        panel.register(user2);
        panel.register(user3);

        // Create devices using Factory Method pattern
        DeviceCreator thermostatCreator = new ThermostatCreator();
        DeviceCreator lightCreator = new LightCreator();
        DeviceCreator acCreator = new AirConditionerCreator();
        DeviceCreator cameraCreator = new CameraCreator();

        // Add Devices
        panel.addDevice(thermostatCreator.createDevice("Living Room Thermostat"));
        panel.addDevice(lightCreator.createDevice("Kitchen Light"));
        panel.addDevice(acCreator.createDevice("Bedroom AC"));
        panel.addDevice(cameraCreator.createDevice("Hall Camera"));

        System.out.println("\n--- System Initialized ---");

        // Turn all devices ON
        System.out.println("\n--- Turning All Devices ON ---");
        Command turnAllOn = new TurnAllOnCommand();
        turnAllOn.execute();

        // Switch to Away Mode (includes internal notification)
        System.out.println("\n--- Switching to AWAY MODE ---");
        panel.setMode(new AwayMode());

        // Switch to Pet Mode (includes internal notification)
        System.out.println("\n--- Switching to PET MODE ---");
        panel.setMode(new PetMode());

        // Switch to Active Mode (includes internal notification)
        System.out.println("\n--- Switching to ACTIVE MODE (Do Not Disturb) ---");
        panel.setMode(new ActiveMode());

        // Turn all devices OFF
        System.out.println("\n--- Turning All Devices OFF ---");
        Command turnAllOff = new TurnAllOffCommand();
        turnAllOff.execute();

        // Execute individual device commands
        System.out.println("\n--- Executing Device-Specific Commands ---");
        SmartDevice thermostat = panel.getDevices().get(0);
        SmartDevice camera = panel.getDevices().get(3);

        Command setTemp = new DeviceSpecificCommand(thermostat, "temperature", "22");
        Command setResolution = new DeviceSpecificCommand(camera, "resolution", "4K");

        setTemp.execute();
        setResolution.execute();

        // List all devices using Iterator
        System.out.println("\n--- Current Devices ---");
        for (SmartDevice d : panel.getDevices()) {
            System.out.println("Device: " + d.getClass().getSimpleName() + " - " + d.getName());
        }

        System.out.println("\n--- Demo Completed ---");
    }
}
