package strategy;

import users.User;

public class ActiveMode implements NotificationStrategy {
    public void sendNotification(User user, String message) {
        // No notifications
    }
}