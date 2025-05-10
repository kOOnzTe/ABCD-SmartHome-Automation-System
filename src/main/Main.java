package main;

import command.Command;
import command.DeviceSpecificCommand;
import command.TurnAllOffCommand;
import command.TurnAllOnCommand;
import controlPanel.MainControlPanel;
import devices.SmartDevice;
import factory.AirConditionerCreator;
import factory.CameraCreator;
import factory.DeviceCreator;
import factory.LightCreator;
import factory.ThermostatCreator;
import strategy.ActiveMode;
import strategy.AwayMode;
import strategy.PetMode;
import users.Admin;
import users.Child;
import users.Parent;
import users.User;

public class Main {
    public static void main(String[] args) {
        MainControlPanel panel = MainControlPanel.getInstance();

        // Add Users
        User admin = new Admin("Alice");
        User parent = new Parent("Bob");
        User child = new Child("Charlie");
        panel.addUser(admin);
        panel.addUser(parent);
        panel.addUser(child);

        // Create devices using Factory Method pattern (Concrete Creators)
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

        // Simulate Away Mode
        System.out.println("\n--- Switching to AWAY MODE ---");
        panel.setMode(new AwayMode());
        panel.notifyUsers("Motion detected in the hallway!");

        // Simulate Pet Mode
        System.out.println("\n--- Switching to PET MODE ---");
        panel.setMode(new PetMode());
        panel.notifyUsers("Pet detected in the living room.");
        panel.notifyUsers("Unknown motion detected in the bedroom!");

        // Simulate Active Mode
        System.out.println("\n--- Switching to ACTIVE MODE (Do Not Disturb) ---");
        panel.setMode(new ActiveMode());
        panel.notifyUsers("Motion detected outside the door.");

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

        // Iterate over devices manually (just to show the iterator)
        System.out.println("\n--- Current Devices ---");
        for (SmartDevice d : panel.getDevices()) {
            System.out.println("Device: " + d.getClass().getSimpleName() + " - " + d.getName());
        }

        System.out.println("\n--- Demo Completed ---");
    }
}
