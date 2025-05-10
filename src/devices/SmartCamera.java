package devices;

public class SmartCamera extends SmartDevice {
    private String resolution;

    public SmartCamera(String name) {
        super(name);
        this.resolution = "720p";
    }

    @Override
    public void executeCommand(String type, String value) {
        if (type.equalsIgnoreCase("resolution")) {
            System.out.println(" resolution set to " + value);
        }
    }
}