package strategy;

import controlPanel.MainControlPanel;
import users.User;
import users.Admin;
import java.util.List;
import iterator.NotificationIterator;

public class AwayMode implements NotificationStrategy {
	
    @Override
    public void sendNotification() {
		MainControlPanel panel = MainControlPanel.getInstance();
		List<User> usersToNotify = panel.getRegisteredUsers();

		for (User user : new NotificationIterator(usersToNotify)) {
			user.update("Notification(Away Mode): Someone is detected in your home!");
		}

		Admin.getInstance().update("Notification(Away Mode -> Admin Only): Someone is detected in your home!");
    }
}