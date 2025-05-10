package devices;

public class SmartCamera extends SmartDevice {
    private String resolution;

    public SmartCamera(String name, String resolution) {
        super(name);
        this.resolution = resolution;
    }

    @Override
    public void executeCommand(String type, String value) {
        if (type.equalsIgnoreCase("changeresolution")) {
            resolution = value;
            System.out.println(name + " resolution set to " + resolution);
        }
    }
}