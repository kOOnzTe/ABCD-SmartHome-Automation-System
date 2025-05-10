package factory;

import devices.SmartCamera;
import devices.SmartDevice;

public class CameraCreator extends DeviceCreator {
    public SmartDevice createDevice(String name) {
        return new SmartCamera(name);
    }
}