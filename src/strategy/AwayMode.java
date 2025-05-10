package strategy;

import controlPanel.MainControlPanel;

public class AwayMode implements NotificationStrategy {
	public void sendNotification(String message) {
		MainControlPanel mainControlPanel = MainControlPanel.getInstance();

    	mainControlPanel.notifyUsers(message);
	}
}