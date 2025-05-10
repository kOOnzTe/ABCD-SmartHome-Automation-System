package factory;

import devices.SmartDevice;
import devices.SmartLight;

public class LightCreator extends DeviceCreator {
    public SmartDevice createDevice(String name) {
        return new SmartLight(name);
    }
}