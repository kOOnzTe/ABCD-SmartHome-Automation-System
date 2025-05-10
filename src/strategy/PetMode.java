package strategy;

import controlPanel.MainControlPanel;
import users.Admin; // For instanceof check
import users.User;
import java.util.List;

public class PetMode implements NotificationStrategy {
	
    @Override
    public void sendNotification(String message) {
		MainControlPanel panel = MainControlPanel.getInstance();
		boolean isPetDetected = panel.isPetDetected();
		List<User> registeredUsers = panel.getRegisteredUsers();

		for (User user : registeredUsers) {
			boolean isAdmin = user instanceof Admin;

			if (isAdmin) {
				user.update("PetMode (Admin Alert): " + message); // Admin always receives
			} else {
				// For non-admins, notify only if no pet is detected
				if (!isPetDetected) {
					user.update("PetMode (No Pet Detected): " + message);
				} else {
					// Optional: log suppression for debugging
					// System.out.println("PetMode: Notification for user " + user.getName() + " ('" + message + "') suppressed due to pet detection.");
				}
			}
		}
    }
}