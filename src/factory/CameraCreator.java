package factory;

import devices.SmartCamera;
import devices.SmartDevice;

public class CameraCreator extends DeviceCreator {
    public SmartDevice createDevice(String name, String resolution) {
        return new SmartCamera(name, resolution);
    }
}