package strategy;

import users.User;

public interface NotificationStrategy {
    void sendNotification(User user, String message);
}