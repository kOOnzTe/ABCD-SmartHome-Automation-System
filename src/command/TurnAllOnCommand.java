package command;

import controlPanel.MainControlPanel;

public class TurnAllOnCommand implements Command {
    public void execute() {
        MainControlPanel.getInstance().turnAllDevicesOn();
    }
}