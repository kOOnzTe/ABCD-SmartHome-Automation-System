package strategy;

// No MainControlPanel import needed if it does nothing directly with it.

public class ActiveMode implements NotificationStrategy {
	
    @Override
    public void sendNotification(String message) {
    	// In Active Mode, all notifications are suppressed. Do nothing.
        // System.out.println("ActiveMode: Notification '" + message + "' intentionally suppressed."); // For debug
    }
}