package factory;

import devices.SmartDevice;

public abstract class DeviceCreator {
    public abstract SmartDevice createDevice(String name);
}