package command;

import devices.SmartDevice;

public class DeviceSpecificCommand implements Command {
    private final SmartDevice device;
    private final String commandType;
    private final String value;

    public DeviceSpecificCommand(SmartDevice device, String commandType, String value) {
        this.device = device;
        this.commandType = commandType;
        this.value = value;
    }

    @Override
    public void execute() {
        device.executeCommand(commandType, value);
    }
}