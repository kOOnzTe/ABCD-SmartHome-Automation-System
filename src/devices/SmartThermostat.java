package devices;

public class SmartThermostat extends SmartDevice {
    private int temperatureCheckInterval;

    public SmartThermostat(String name) {
        super(name);
        temperatureCheckInterval = 3;
    }

    @Override
    public void executeCommand(String type, String value) {
        if (type.equalsIgnoreCase("temperature")) {
            System.out.println( " set temperature to " + value + "°C");
        }
    }
}