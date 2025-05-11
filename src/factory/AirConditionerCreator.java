package factory;

import devices.SmartAirConditioner;
import devices.SmartDevice;

public class AirConditionerCreator extends DeviceCreator {
    @Override
    public SmartDevice createDevice(String name, String fanSpeed) {
        return new SmartAirConditioner(name, fanSpeed);
    }
}