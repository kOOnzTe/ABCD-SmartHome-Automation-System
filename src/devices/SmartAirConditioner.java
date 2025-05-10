package devices;

public class SmartAirConditioner extends SmartDevice {
    public SmartAirConditioner(String name) {
        super(name);
    }

    @Override
    public void executeCommand(String type, String value) {
        if (type.equalsIgnoreCase("fanspeed")) {
            System.out.println(name + " fan speed set to " + value);
        }
    }
}