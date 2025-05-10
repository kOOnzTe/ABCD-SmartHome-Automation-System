package strategy;

import users.User;

public class AwayMode implements NotificationStrategy {
    public void sendNotification(User user, String message) {
        user.update("[Away Mode] " + message);
    }
}