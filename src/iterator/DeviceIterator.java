package iterator;

import java.util.Iterator;
import java.util.List;

import devices.SmartDevice;

public class DeviceIterator implements Iterable<SmartDevice> {
    private final List<SmartDevice> devices;

    public DeviceIterator(List<SmartDevice> devices) {
        this.devices = devices;
    }

    @Override
    public Iterator<SmartDevice> iterator() {
        return devices.iterator();
    }
}