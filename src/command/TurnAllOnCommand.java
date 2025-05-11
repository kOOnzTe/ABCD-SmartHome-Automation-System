package command;

import controlPanel.MainControlPanel;

public class TurnAllOnCommand implements Command {
    @Override
    public void execute() {
        MainControlPanel.getInstance().turnAllDevicesOn();
    }
}