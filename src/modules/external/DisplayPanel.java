package modules.external;


import modules.Module;
import tuc.ece.cs201.vm.hw.device.DisplayDevice;

public class DisplayPanel extends Module<DisplayDevice> {

    //Constructor
    public DisplayPanel(DisplayDevice device) {
        super(device);
        setName(getClass().getSimpleName());
    }

    //Other Methods
    public void displayMessage(String message) {
        getDevice().displayMsg(message);
    }
}
