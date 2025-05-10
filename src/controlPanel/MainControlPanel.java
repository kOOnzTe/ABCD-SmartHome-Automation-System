package controlPanel;

import java.util.ArrayList;
import java.util.List;

import devices.SmartDevice;
import iterator.DeviceIterator;
import strategy.ActiveMode;
import strategy.NotificationStrategy;
import strategy.PetMode;
import users.User;

public class MainControlPanel implements SubjectInterface {
	private static MainControlPanel instance;
	private List<SmartDevice> devices = new ArrayList<>();
	private List<User> users = new ArrayList<>();
	private NotificationStrategy mode = new ActiveMode();
	private List<User> registeredUsers = new ArrayList<>();

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

	public void notifyUsers(String message) {
		notifyRegisteredUsers(message);
	}

	public void setMode(NotificationStrategy mode) {
		this.mode = mode;
		String message = "Active mode activated!";
		if(mode instanceof PetMode)
			message = "Pet mode activated!";
		notifyUsers(message);
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

	@Override
	public void register(User user) {
		if (!registeredUsers.contains(user)) {
			registeredUsers.add(user);
		}
	}

	@Override
	public void unregister(User user) {
		registeredUsers.remove(user);
	}

	@Override
	public void notifyRegisteredUsers(String message) {
		if (mode instanceof ActiveMode || mode instanceof PetMode) {
			for (int i = 0; i < registeredUsers.size(); i++) {
				registeredUsers.get(i).update("updated: " + message);
			}
		}

	}
}
