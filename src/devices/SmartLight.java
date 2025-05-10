package devices;

public class SmartLight extends SmartDevice {
    private int brightness;

    public SmartLight(String name) {
        super(name);
        this.brightness = 1000;
    }

    @Override
    public void executeCommand(String type, String value) {
        if (type.equalsIgnoreCase("brightness")) {
            System.out.println(" brightness set to " + value);
        }
    }
}