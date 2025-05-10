package strategy;

import controlPanel.MainControlPanel;
import users.User; // Assuming User class is in users package
import java.util.List;

public class AwayMode implements NotificationStrategy {
	
    @Override
    public void sendNotification(String message) {
		MainControlPanel panel = MainControlPanel.getInstance();
		List<User> usersToNotify = panel.getRegisteredUsers();

		// "All registered users receive device notifications."
        // "It is important to remember that the admin always receives notifications."
        // If admin is always a registered user, this loop covers the admin as well.
		for (User user : usersToNotify) {
			user.update("AwayMode Notification: " + message);
		}
    }
}