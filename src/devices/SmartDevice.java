package devices;

public abstract class SmartDevice {
    protected String name;
    protected boolean isOn = false;

    public SmartDevice(String name) {
        this.name = name;
    }

    public void turnOn() {
        isOn = true;
        System.out.println(name + " is turned ON.");
    }

    public void turnOff() {
        isOn = false;
        System.out.println(name + " is turned OFF.");
    }

    public boolean isOn() {
        return this.isOn;
    }
    
    public String getName() {
        return name;
    }

    public abstract void executeCommand(String type, String value);

}