package strategy;

import controlPanel.MainControlPanel;

public class PetMode implements NotificationStrategy {
	public void sendNotification(String message) {
		MainControlPanel mainControlPanel = MainControlPanel.getInstance();

    	mainControlPanel.notifyUsers(message);
	}
}