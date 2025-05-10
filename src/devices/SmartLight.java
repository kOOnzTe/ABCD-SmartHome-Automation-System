package devices;

public class SmartLight extends SmartDevice {
    public SmartLight(String name) {
        super(name);
    }

    @Override
    public void executeCommand(String type, String value) {
        if (type.equalsIgnoreCase("brightness")) {
            System.out.println(name + " brightness set to " + value);
        }
    }
}