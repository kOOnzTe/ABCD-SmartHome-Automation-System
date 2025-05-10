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

public class ABCDSmartHomeAutomationSystem {
    public static void main(String[] args) {
        System.out.println("Welcome to ABCD Smart Home Automation System!");

        // Design Pattern 1: Singleton with Lazy Initialization
        System.out.println("\n--- Demonstrating Singleton Pattern (MainControlPanel) ---");
        System.out.println("Attempting to get instance of MainControlPanel.");
        System.out.println("The MainControlPanel is designed as a Singleton to ensure only one central control unit exists.");
        System.out.println("It should be instantiated only when first needed (Lazy Initialization).");
        MainControlPanel panel = MainControlPanel.getInstance();
        System.out.println("MainControlPanel instance obtained: " + panel.hashCode());
        System.out.println("Attempting to get another instance of MainControlPanel to demonstrate Singleton property.");
        MainControlPanel panel2 = MainControlPanel.getInstance();
        System.out.println("Second MainControlPanel instance obtained: " + panel2.hashCode());
        System.out.println("Both instances refer to the same object, confirming Singleton pattern: " + (panel == panel2));
        System.out.println("--- Singleton Pattern Demonstration Complete ---");

        System.out.println("\n--- Initializing Users (for Observer Pattern later) ---");
        User user1 = new User("Alice (Admin)", false);
        User user2 = new User("Bob (Registered User)", false);
        User user3 = new User("Charlie (Child User - Unregistered for general notifications)", true);

        // Design Pattern 4: Observer (User Registration part)
        System.out.println("\n--- Demonstrating Observer Pattern (User Registration) ---");
        System.out.println("The Admin user is automatically set and always receives notifications.");
        Admin.setInstance(user1); // Alice is the admin
        System.out.println(user1.getName() + " set as Admin.");

        System.out.println("Registering users with the MainControlPanel to receive notifications.");
        System.out.println("Users (Observers) register with the MainControlPanel (Subject).");
        panel.register(user1); // Admin also registers as a standard observer for some notifications
        panel.register(user2);
        panel.register(user3);
        System.out.println(user2.getName() + " and " + user3.getName() + " registered as observers.");
        System.out.println("--- Observer Pattern (User Registration) Demonstration Complete ---");


        // Design Pattern 2: Factory Method
        System.out.println("\n--- Demonstrating Factory Method Pattern (Device Creation) ---");
        System.out.println("Using dedicated DeviceCreator factories to create smart devices.");
        System.out.println("This encapsulates device creation logic and allows adding new device types without altering the panel.");

        DeviceCreator thermostatCreator = new ThermostatCreator();
        System.out.println("ThermostatCreator instantiated.");
        SmartDevice thermostat = thermostatCreator.createDevice("Living Room Thermostat");
        System.out.println("ThermostatCreator produced: " + thermostat.getName() + " (" + thermostat.getClass().getSimpleName() + ")");
        panel.addDevice(thermostat);

        DeviceCreator lightCreator = new LightCreator();
        System.out.println("LightCreator instantiated.");
        SmartDevice light = lightCreator.createDevice("Kitchen Light");
        System.out.println("LightCreator produced: " + light.getName() + " (" + light.getClass().getSimpleName() + ")");
        panel.addDevice(light);

        DeviceCreator acCreator = new AirConditionerCreator();
        System.out.println("AirConditionerCreator instantiated.");
        SmartDevice ac = acCreator.createDevice("Bedroom AC");
        System.out.println("AirConditionerCreator produced: " + ac.getName() + " (" + ac.getClass().getSimpleName() + ")");
        panel.addDevice(ac);

        DeviceCreator cameraCreator = new CameraCreator();
        System.out.println("CameraCreator instantiated.");
        SmartDevice camera = cameraCreator.createDevice("Hall Camera");
        System.out.println("CameraCreator produced: " + camera.getName() + " (" + camera.getClass().getSimpleName() + ")");
        panel.addDevice(camera);
        System.out.println("All initial devices created and added to the control panel.");
        System.out.println("--- Factory Method Pattern Demonstration Complete ---");

        System.out.println("\n--- System Initialized with Devices and Users ---");

        // Design Pattern 5: Command
        System.out.println("\n--- Demonstrating Command Pattern (Bulk Operations) ---");
        System.out.println("Actions like 'Turn All On/Off' are encapsulated as Command objects.");
        System.out.println("The panel invokes execute() on the command, decoupling request from execution.");

        System.out.println("\nExecuting 'Turn All Devices ON' Command:");
        Command turnAllOn = new TurnAllOnCommand();
        System.out.println("Instantiated TurnAllOnCommand. This command will iterate through all devices (implicit Iterator pattern).");
        turnAllOn.execute();
        System.out.println("'Turn All Devices ON' Command executed.");
        System.out.println("--- Command Pattern (Bulk Operations) Demonstration Complete ---");

        // Design Pattern 6: Strategy & Design Pattern 4: Observer (Notifications)
        System.out.println("\n--- Demonstrating Strategy Pattern (Control Panel Modes) & Observer Pattern (Notifications) ---");
        System.out.println("The Control Panel uses different Strategies for notification handling in various modes.");
        System.out.println("When mode changes, the panel notifies registered Observers based on the current Strategy.");

        System.out.println("\nSwitching to AWAY MODE:");
        System.out.println("AwayMode Strategy: Full notifications for registered users if camera detects movement. Admin always notified.");
        panel.setMode(new AwayMode());
        System.out.println("Simulating camera detecting movement in Away Mode (conceptual).");
        // At this point, if the AwayMode strategy and camera/panel logic are set up,
        // notifications would be sent to user1 (Admin) and user2.
        // For example, panel might call a method like: panel.notifyObservers("Camera detected movement!");

        System.out.println("\nSwitching to PET MODE:");
        System.out.println("PetMode Strategy: Notifications for movement, but suppressed if a pet is detected. Admin always notified.");
        panel.setMode(new PetMode());
        System.out.println("Simulating camera detecting movement with a pet in Pet Mode (conceptual).");
        // Here, notifications might be suppressed for user2 if a pet is part of the event context.
        // Admin (user1) would still get notified as per scope.

        System.out.println("\nSwitching to ACTIVE MODE (Do Not Disturb):");
        System.out.println("ActiveMode Strategy: All notifications disabled (except for Admin, if system rules dictate based on scope).");
        panel.setMode(new ActiveMode());
        System.out.println("Simulating camera detecting movement in Active Mode (conceptual).");
        // No notifications for user2. Admin might still get critical ones or all based on exact rules.
        System.out.println("--- Strategy & Observer (Notifications) Pattern Demonstration Complete ---");

        System.out.println("\n--- Demonstrating Command Pattern (Device-Specific Operations) ---");
        System.out.println("Device-specific actions are also encapsulated as Command objects.");
        SmartDevice livingRoomThermostat = panel.getDevices().get(0);
        SmartDevice hallCamera = panel.getDevices().get(3);

        System.out.println("\nExecuting 'Set Temperature' Command for " + livingRoomThermostat.getName() + ":");
        Command setTemp = new DeviceSpecificCommand(livingRoomThermostat, "temperature", "22");
        System.out.println("Instantiated DeviceSpecificCommand for setting temperature.");
        setTemp.execute();
        System.out.println("'Set Temperature' Command executed.");

        System.out.println("\nExecuting 'Set Resolution' Command for " + hallCamera.getName() + ":");
        Command setResolution = new DeviceSpecificCommand(hallCamera, "resolution", "4K");
        System.out.println("Instantiated DeviceSpecificCommand for setting camera resolution.");
        setResolution.execute();
        System.out.println("'Set Resolution' Command executed.");
        System.out.println("--- Command Pattern (Device-Specific) Demonstration Complete ---");


        System.out.println("\n--- Demonstrating Command Pattern (Bulk Operations - Turn Off) ---");
        System.out.println("Executing 'Turn All Devices OFF' Command:");
        Command turnAllOff = new TurnAllOffCommand();
        System.out.println("Instantiated TurnAllOffCommand. This command will iterate through all devices (implicit Iterator pattern).");
        turnAllOff.execute();
        System.out.println("'Turn All Devices OFF' Command executed.");
        System.out.println("--- Command Pattern (Bulk Operations - Turn Off) Demonstration Complete ---");


        // Design Pattern 3: Iterator
        System.out.println("\n--- Demonstrating Iterator Pattern (Listing Devices) ---");
        System.out.println("The MainControlPanel provides an iterator to traverse its list of devices for operations like generating reports.");
        System.out.println("Iterating through all registered devices to display their status/info:");
        int deviceCount = 0;
        for (SmartDevice d : panel.getDevices()) {
            System.out.println("Device: " + d.getClass().getSimpleName() + " - " + d.getName() + " | Status: " + (d.isOn() ? "ON" : "OFF"));
            deviceCount++;
        }
        System.out.println("Total devices iterated: " + deviceCount);
        System.out.println("--- Iterator Pattern Demonstration Complete ---");


        System.out.println("\n--- ABCD Smart Home Automation System Demo Completed ---");
    }
}
