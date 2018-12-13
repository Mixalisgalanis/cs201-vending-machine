package machine.swing;

import devices.swingDevices.external.SwingNumPadDevice;

import javax.swing.*;
import java.awt.*;

public class ExternalDevices extends JPanel {

    SwingNumPadDevice numPadDevice;

    public ExternalDevices() {
        setBackground(Color.DARK_GRAY);
        setPreferredSize(new Dimension((int) (SwingMachine.EXTERNAL_INTERNAL_RATIO * SwingMachine.FRAME_SIZE_WIDTH),
                SwingMachine.FRAME_SIZE_HEIGHT));
        numPadDevice = new SwingNumPadDevice();
        add(numPadDevice);
        SwingMachine.getInstance().addDevice(numPadDevice);
    }

}
