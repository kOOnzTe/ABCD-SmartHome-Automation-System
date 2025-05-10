package devices;

public class SmartThermostat extends SmartDevice {
    private String temperatureCheckInterval;

    public SmartThermostat(String name, String temperatureCheckInterval) {
        super(name);
        this.temperatureCheckInterval = temperatureCheckInterval;
    }

    @Override
    public void executeCommand(String type, String value) {
        if (type.equalsIgnoreCase("changecheckinterval")) {
            temperatureCheckInterval = value;
            System.out.println(name + " set temperature check interval to " + temperatureCheckInterval);
        }
    }
}