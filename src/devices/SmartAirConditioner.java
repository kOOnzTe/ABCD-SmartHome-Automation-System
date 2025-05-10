package devices;

public class SmartAirConditioner extends SmartDevice {
    private String fanSpeed;

    public SmartAirConditioner(String name) {
        super(name);
        fanSpeed = "Medium";
    }

    @Override
    public void executeCommand(String type, String value) {
        if (type.equalsIgnoreCase("changefanspeed")) {
            fanSpeed = value;
            System.out.println(name + " fan speed set to " + fanSpeed);
        }
    }
}