package strategy;

import users.Admin;

public class ActiveMode implements NotificationStrategy {
	
    @Override
    public void sendNotification() {
        Admin.getInstance().update("Notification(Active Mode -> Admin Only): Something is detected in your home!");
    }
}