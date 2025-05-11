package command;

import controlPanel.MainControlPanel;

public class TurnAllOffCommand implements Command {
    @Override
    public void execute() {
        MainControlPanel.getInstance().turnAllDevicesOff();
    }
}