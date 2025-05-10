package strategy;

import controlPanel.MainControlPanel;
import users.Admin; // For instanceof check
import users.User;
import java.util.List;

public class PetMode implements NotificationStrategy {
	
    @Override
    public void sendNotification() {
		MainControlPanel panel = MainControlPanel.getInstance();
		boolean isPetDetected = panel.isPetDetected();
		List<User> registeredUsers = panel.getRegisteredUsers();

		for (User user : registeredUsers) {
			boolean isAdmin = user instanceof Admin;

			if (isAdmin) {
				user.update("Notification(Pet Mode): Something is detected in your home!" ); // Admin always receives
			} else {
				// For non-admins, notify only if no pet is detected
				if (!isPetDetected) {
					user.update("Notification(Pet Mode): Someone is detected in your home!" );
				}
			}
		}
    }
}