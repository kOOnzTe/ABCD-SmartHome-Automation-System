package main;

import command.*;
import controlPanel.MainControlPanel;
import devices.SmartDevice;
import factory.*;
import strategy.ActiveMode;
import strategy.AwayMode;
import strategy.PetMode;
import users.*;

public class ABCDSmartHomeAutomationSystem {
    public static void main(String[] args) {
        System.out.println("Welcome to ABCD Smart Home Automation System!");
        // Note: The control panel and its users will only belong to one home.

        // Design Pattern 1: Singleton with Lazy Initialization
        System.out.println("\n--- Demonstrating Singleton Pattern (MainControlPanel) ---");
        System.out.println("A request for panel work has been received.");
        MainControlPanel panel = MainControlPanel.getInstance();
        System.out.println("MainControlPanel instance obtained: " + panel.hashCode());
        System.out.println("Second request for panel work has been received.");
        // this will not create a new instance, but return the existing one.
        MainControlPanel panel2 = MainControlPanel.getInstance();
        System.out.println("Second MainControlPanel instance obtained: " + panel2.hashCode());

        // Design Pattern 2: Factory Method
        System.out.println("\n--- Demonstrating Factory Method Pattern (Device Creation) ---");
        DeviceCreator thermostatCreator = new ThermostatCreator();
        System.out.println("ThermostatCreator instantiated.");
        SmartDevice thermostat = thermostatCreator.createDevice("Living Room Thermostat", "5");
        System.out.println("ThermostatCreator produced: " + thermostat.getName() + " (" + thermostat.getClass().getSimpleName() + ")");
        panel.addDevice(thermostat);

        DeviceCreator lightCreator = new LightCreator();
        System.out.println("LightCreator instantiated.");
        SmartDevice light = lightCreator.createDevice("Kitchen Light", "800 Lumen");
        System.out.println("LightCreator produced: " + light.getName() + " (" + light.getClass().getSimpleName() + ")");
        panel.addDevice(light);

        DeviceCreator acCreator = new AirConditionerCreator();
        System.out.println("AirConditionerCreator instantiated.");
        SmartDevice ac = acCreator.createDevice("Bedroom AC", "Medium");
        System.out.println("AirConditionerCreator produced: " + ac.getName() + " (" + ac.getClass().getSimpleName() + ")");
        panel.addDevice(ac);

        DeviceCreator cameraCreator = new CameraCreator();
        System.out.println("CameraCreator instantiated.");
        SmartDevice camera = cameraCreator.createDevice("Hall Camera", "720p");
        System.out.println("CameraCreator produced: " + camera.getName() + " (" + camera.getClass().getSimpleName() + ")");
        panel.addDevice(camera);

        System.out.println("\n--- Initializing Users (for Observer Pattern later) ---");
        System.out.println("Creating user Alice");
        User user1 = new User("Alice", false);
        panel.addUser(user1);
        System.out.println("Creating user Bob");
        User user2 = new User("Bob", false);
        panel.addUser(user2);
        System.out.println("Creating user Charlie (is a child)");
        User user3 = new User("Charlie", true);
        panel.addUser(user3);
        System.out.println("Creating admin");
        Admin admin = Admin.getInstance();
        panel.addUser(admin);

        // Design Pattern 4: Observer (User Registration part)
        System.out.println("\n--- Demonstrating Observer Pattern (User Registration) ---");
        System.out.println("Registering users with the MainControlPanel to receive notifications. (Charlie is not registered as an observer since he is a child)");
        panel.register(user1);
        panel.register(user2);
        panel.register(user3); // Charlie is will not be registered as an observer since he is a child
        // No need to register admin as an observer, since he is always notified.


        System.out.println("\n--- System Initialized with Devices and Users ---");

        // Design Pattern 5: Command
        System.out.println("\n--- Demonstrating Command Pattern (Bulk Operations) ---");
        System.out.println("Actions like 'Turn All On/Off' are encapsulated as Command objects.");

        System.out.println("\nExecuting 'Turn All Devices ON' Command:");
        Command turnAllOn = new TurnAllOnCommand();
        turnAllOn.execute();

        // Design Pattern 6: Strategy & Design Pattern 4: Observer (Notifications)
        System.out.println("\n--- Demonstrating Strategy Pattern (Control Panel Modes) & Observer Pattern (Notifications) ---");

        System.out.println("\nSwitching to AWAY MODE:");
        System.out.println("AwayMode Strategy: Full notifications for registered users if camera detects movement. Admin always notified.");
        panel.setMode(new AwayMode());
        System.out.println("Camera is detecting movement.");
        panel.notifyUsers();

        System.out.println("\nSwitching to PET MODE:");
        System.out.println("PetMode Strategy: Notifications for movement, but suppressed if a pet is detected. Admin always notified.");
        panel.setMode(new PetMode());
        System.out.println("Camera is detecting movement of a pet.");
        panel.setPetDetected(true);
        panel.notifyUsers();
        System.out.println("Camera is detecting movement of something other than a pet.");
        panel.setPetDetected(false);
        panel.notifyUsers();
        

        System.out.println("\nSwitching to ACTIVE MODE:");
        panel.setMode(new ActiveMode());
        System.out.println("ActiveMode Strategy: All notifications disabled. Admin always notified.");
        System.out.println("Camera is detecting movement.");
        panel.notifyUsers();


        System.out.println("\nSwitching to AWAY MODE");
        panel.setMode(new AwayMode());
        System.out.println("AwayMode Strategy: Full notifications for registered users if camera detects movement. Admin always notified.");
        System.out.println("User2 is removed from observer list, so he will not receive notifications.");
        panel.unregister(user2);
        System.out.println("Simulating camera detecting movement in Away Mode (conceptual)."); 
        panel.notifyUsers();


        System.out.println("\n--- Demonstrating Command Pattern (Device-Specific Operations) ---");
        System.out.println("Device-specific actions are also encapsulated as Command objects.");
        SmartDevice livingRoomThermostat = panel.getDevices().get(0);
        SmartDevice hallCamera = panel.getDevices().get(3);

        System.out.println("\nExecuting 'Set Temperature' Command for " + livingRoomThermostat.getName() + ":");
        Command changecheckinterval = new DeviceSpecificCommand(livingRoomThermostat, "changecheckinterval", "5");
        System.out.println("Instantiated changeCheckInterval for setting check interval.");
        changecheckinterval.execute();
        System.out.println("'changeCheckInterval' Command executed.");

        System.out.println("\nExecuting 'Set Resolution' Command for " + hallCamera.getName() + ":");
        Command setResolution = new DeviceSpecificCommand(hallCamera, "changeresolution", "1080p");
        System.out.println("Instantiated DeviceSpecificCommand for setting camera resolution.");
        setResolution.execute();
        System.out.println("'Set Resolution' Command executed.");

        System.out.println("\nExecuting 'Adjust Fan Speed' Command for " + ac.getName() + ":");
        Command adjustFanSpeed = new DeviceSpecificCommand(ac, "adjustfanspeed", "High");
        System.out.println("Instantiated DeviceSpecificCommand for adjusting fan speed.");
        adjustFanSpeed.execute();
        System.out.println("'Adjust Fan Speed' Command executed.");

        System.out.println("\nExecuting 'Change Brightness' Command for " + light.getName() + ":");
        Command changeBrightness = new DeviceSpecificCommand(light, "changebrightness", "100%");
        System.out.println("Instantiated DeviceSpecificCommand for changing brightness.");
        changeBrightness.execute();
        System.out.println("'Change Brightness' Command executed.");

        // Design Pattern 3: Iterator
        System.out.println("\n--- Demonstrating Iterator Pattern (Listing Devices) ---");
        System.out.println("\nIterating through all registered devices to display their status/info:");
        panel.getDevicesStatus();

        System.out.println("\nTurning off all devices:");
        Command turnAllOff = new TurnAllOffCommand();
        turnAllOff.execute();

        System.out.println("\nIterating through all registered devices to display their status/info:");
        panel.getDevicesStatus();


        System.out.println("\n--- ABCD Smart Home Automation System Demo Completed ---");
    }
}
