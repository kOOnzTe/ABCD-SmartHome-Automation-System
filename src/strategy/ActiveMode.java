package strategy;

import users.Admin;
// No MainControlPanel import needed if it does nothing directly with it.

public class ActiveMode implements NotificationStrategy {
	
    @Override
    public void sendNotification() {
        Admin.getInstance().update("Notification(Active Mode -> Admin Only): Something is detected in your home!");
    }
}