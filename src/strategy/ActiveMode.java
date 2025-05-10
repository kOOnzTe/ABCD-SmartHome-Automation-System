package strategy;

// No MainControlPanel import needed if it does nothing directly with it.

public class ActiveMode implements NotificationStrategy {
	
    @Override
    public void sendNotification() {
    	// In Active Mode, all notifications are suppressed. Do nothing even if you are admin.
    }
}