package devices;

public class SmartAirConditioner extends SmartDevice {
    private FanSpeed fanSpeed;

    public SmartAirConditioner(String name) {
        super(name);
        fanSpeed = FanSpeed.MEDIUM;
    }

    @Override
    public void executeCommand(String type, String value) {
        if (type.equalsIgnoreCase("fanspeed")) {
            System.out.println(" fan speed set to " + value);
        }
    }
}