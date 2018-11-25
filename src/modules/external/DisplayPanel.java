package modules.external;

import devices.external.DisplayDevice;
import modules.Module;

public class DisplayPanel extends Module<DisplayDevice> {

    //Constructor
    public DisplayPanel(DisplayDevice device) {
        super("DisplayPanel", device);
    }

    //Other Methods
    public void displayMessage(String message) {
        getDevice().displayMsg(message);
    }
}
