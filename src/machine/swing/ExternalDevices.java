package machine.swing;

import devices.swingDevices.external.SwingChangeCaseDevice;
import devices.swingDevices.external.SwingDisplayDevice;
import devices.swingDevices.external.SwingNumPadDevice;

import javax.swing.*;
import java.awt.*;

public class ExternalDevices extends JPanel {

    SwingNumPadDevice numPadDevice;
    SwingDisplayDevice displayDevice;
    SwingChangeCaseDevice changeCaseDevice;

    public ExternalDevices() {
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        setPreferredSize(new Dimension((int) (SwingMachine.EXTERNAL_INTERNAL_RATIO * SwingMachine.FRAME_SIZE_WIDTH),
                SwingMachine.FRAME_SIZE_HEIGHT));
        numPadDevice = new SwingNumPadDevice();
        displayDevice = new SwingDisplayDevice();
        changeCaseDevice = new SwingChangeCaseDevice();
        add(displayDevice);
        add(numPadDevice);
        add(changeCaseDevice);
        SwingMachine.getInstance().addDevice(numPadDevice);
        SwingMachine.getInstance().addDevice(displayDevice);
        SwingMachine.getInstance().addDevice(changeCaseDevice);
    }

}
