package controlPanel;

import java.util.ArrayList;
import java.util.List;

import devices.SmartDevice;
import iterator.DeviceIterator;
import strategy.ActiveMode;
import strategy.AwayMode;
import strategy.NotificationStrategy;
import strategy.PetMode;
import users.User;

public class MainControlPanel implements SubjectInterface {
	private static MainControlPanel instance;
	private List<SmartDevice> devices = new ArrayList<>();
	private List<User> users = new ArrayList<>();
	private NotificationStrategy mode = new ActiveMode();
	private List<User> registeredUsers = new ArrayList<>();
	private boolean petDetectedState = false;

	private MainControlPanel() {
	}

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

	public void notifyUsers() {
		if (this.mode == null) {
			System.err.println("Error: Notification strategy (mode) is not set.");
			return;
		}
		this.mode.sendNotification();
	}

	public void setMode(NotificationStrategy newMode) {
		this.mode = newMode;

		String modeChangeMessage;
		if (this.mode instanceof ActiveMode) {
			modeChangeMessage = "Active mode (Do Not Disturb) activated!";
		} else if (this.mode instanceof AwayMode) {
			modeChangeMessage = "Away mode activated!";
		} else if (this.mode instanceof PetMode) {
			modeChangeMessage = "Pet mode activated!";
		} else {
			modeChangeMessage = "Switched to an unspecified mode.";
		}

		System.out.println("MainControlPanel System Update: " + modeChangeMessage);
		for (User user : registeredUsers) {
			user.update(modeChangeMessage);
		}
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

	public void turnDeviceOn(SmartDevice d) {
		d.turnOn();
	}
	public void turnDeviceOff(SmartDevice d) {
		d.turnOff();
	}

	public void getDevicesStatus() {
		for (SmartDevice d : new DeviceIterator(devices)) {
			System.out.println("Device: " + d.getClass().getSimpleName() + " - " + d.getName() + " | Status: " + (d.isOn() ? "ON" : "OFF"));
		}
		System.out.println("Total devices iterated: " + devices.size());
	}

	public List<SmartDevice> getDevices() {
		return devices;
	}

	public List<User> getUsers() {
		return users;
	}

	public List<User> getRegisteredUsers() {
		return new ArrayList<>(this.registeredUsers);
	}

	public boolean isPetDetected() {
		return this.petDetectedState;
	}

	public void setPetDetected(boolean detected) {
		this.petDetectedState = detected;
	}

	@Override
	public void register(User user) {
        if (user.getIsChild()) {
            System.out.println(user.getName() + " is a child and is not registered as an observer.");
            return;
        }
		if (!registeredUsers.contains(user)) {
			registeredUsers.add(user);
            System.out.println(user.getName() + " is registered as an observer.");
		}
	}

	@Override
	public void unregister(User user) {
		registeredUsers.remove(user);
	}

	@Override
	public void notifyRegisteredUsers(String message) {
		if (!(mode instanceof ActiveMode)) {
			for (User user : registeredUsers) {
				user.update("updated: " + message);
			}
		}
	}
}
