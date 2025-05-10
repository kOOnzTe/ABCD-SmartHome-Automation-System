package devices;

public class SmartThermostat extends SmartDevice {
    public SmartThermostat(String name) {
        super(name);
    }

    @Override
    public void executeCommand(String type, String value) {
        if (type.equalsIgnoreCase("temperature")) {
            System.out.println(name + " set temperature to " + value + "°C");
        }
    }
}