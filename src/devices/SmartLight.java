package devices;

public class SmartLight extends SmartDevice {
    private String brightness;

    public SmartLight(String name) {
        super(name);
        this.brightness = "1000";
    }

    @Override
    public void executeCommand(String type, String value) {
        if (type.equalsIgnoreCase("changebrightness")) {
            brightness = value;
            System.out.println(name + " brightness set to " + brightness);
        }
    }
}