package factory;

import devices.SmartAirConditioner;
import devices.SmartDevice;

public class AirConditionerCreator extends DeviceCreator {
    public SmartDevice createDevice(String name) {
        return new SmartAirConditioner(name);
    }
}