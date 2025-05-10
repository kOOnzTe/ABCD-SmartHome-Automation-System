package command;

import controlPanel.MainControlPanel;

public class TurnAllOffCommand implements Command {
    public void execute() {
        MainControlPanel.getInstance().turnAllDevicesOff();
    }
}