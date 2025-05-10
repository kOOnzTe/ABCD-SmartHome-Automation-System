package factory;

import devices.SmartDevice;
import devices.SmartLight;

public class LightCreator extends DeviceCreator {
    public SmartDevice createDevice(String name, String brightness) {
        return new SmartLight(name, brightness);
    }
}