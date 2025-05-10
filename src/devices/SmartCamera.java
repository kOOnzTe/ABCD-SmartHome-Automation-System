package devices;

public class SmartCamera extends SmartDevice {
    public SmartCamera(String name) {
        super(name);
    }

    @Override
    public void executeCommand(String type, String value) {
        if (type.equalsIgnoreCase("resolution")) {
            System.out.println(name + " resolution set to " + value);
        }
    }
}