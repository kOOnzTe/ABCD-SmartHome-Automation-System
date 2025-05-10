package controlPanel;

import java.util.ArrayList;
import java.util.List;

import devices.SmartDevice;
import iterator.DeviceIterator;
import strategy.ActiveMode;
import strategy.NotificationStrategy;
import users.User;

public class MainControlPanel {
    private static MainControlPanel instance;
    private List<SmartDevice> devices = new ArrayList<>();
    private List<User> users = new ArrayList<>();
    private NotificationStrategy mode = new ActiveMode();

    private MainControlPanel() {}

    public static synchronized MainControlPanel getInstance() {
        if (instance == null) {
            instance = new MainControlPanel();
        }
        return instance;
    }

    public void addDevice(SmartDevice device) {
        devices.add(device);
    }

    public void addUser(User user) {
        users.add(user);
    }

    public void notifyUsers(String message) {
        for (User user : users) {
            if (user.isAdmin() || user.isRegistered()) {
                mode.sendNotification(user, message);
            }
        }
    }

    public void setMode(NotificationStrategy mode) {
        this.mode = mode;
    }

    public void turnAllDevicesOn() {
        for (SmartDevice d : new DeviceIterator(devices)) {
            d.turnOn();
        }
    }

    public void turnAllDevicesOff() {
        for (SmartDevice d : new DeviceIterator(devices)) {
            d.turnOff();
        }
    }

    public List<SmartDevice> getDevices() {
        return devices;
    }

    public List<User> getUsers() {
        return users;
    }
}

