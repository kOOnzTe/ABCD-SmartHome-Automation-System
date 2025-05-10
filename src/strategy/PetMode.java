package strategy;

import users.User;

public class PetMode implements NotificationStrategy {
    public void sendNotification(User user, String message) {
        if (!message.toLowerCase().contains("pet")) {
            user.update("[Pet Mode] " + message);
        }
    }
}