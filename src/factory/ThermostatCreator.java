package factory;

import devices.SmartDevice;
import devices.SmartThermostat;

public class ThermostatCreator extends DeviceCreator {
    public SmartDevice createDevice(String name) {
        return new SmartThermostat(name);
    }
}