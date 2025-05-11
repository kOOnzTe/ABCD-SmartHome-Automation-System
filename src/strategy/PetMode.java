package strategy;

import controlPanel.MainControlPanel;
import iterator.NotificationIterator;
import java.util.List;
import users.Admin;
import users.User;

public class PetMode implements NotificationStrategy {
	
	@Override
	public void sendNotification() {
		MainControlPanel panel = MainControlPanel.getInstance();
		boolean isPetDetected = panel.isPetDetected();
		List<User> registeredUsers = panel.getRegisteredUsers();

		for (User user : new NotificationIterator(registeredUsers)) {
			if (!isPetDetected) {
				user.update("Notification(Pet Mode): Someone is detected in your home!" );
			}	
		}

		Admin.getInstance().update(getAdminNotificationMessage(isPetDetected));
	}

	private String getAdminNotificationMessage(boolean isPetDetected) {
		return isPetDetected 
			? "Notification(Pet Mode -> Admin Only): Pet is detected in your home!" 
			: "Notification(Pet Mode -> Admin Only): Someone is detected in your home!";
	}
}