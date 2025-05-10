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
			if (!isPetDetected) {
				user.update("Notification(Pet Mode): Someone is detected in your home!" );
			}	
		}
		Admin.getInstance().update("Notification(Pet Mode -> Admin Only): Someone is detected in your home!");
    }
}