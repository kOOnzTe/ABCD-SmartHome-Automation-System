package strategy;

import controlPanel.MainControlPanel;

public class ActiveMode implements NotificationStrategy {
	
    public void sendNotification(String message) {
    	MainControlPanel mainControlPanel = MainControlPanel.getInstance();
    	
    	mainControlPanel.notifyUsers(message);
    }
}