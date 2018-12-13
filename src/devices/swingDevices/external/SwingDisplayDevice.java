package devices.swingDevices.external;

import tuc.ece.cs201.vm.hw.device.Device;
import tuc.ece.cs201.vm.hw.device.DeviceType;
import tuc.ece.cs201.vm.hw.device.DisplayDevice;

import javax.swing.*;
import java.util.List;

public class SwingDisplayDevice extends JPanel implements DisplayDevice {
    //Container Sizes
    private static final int POWDER_CONTAINER_REGULAR_SIZE = 500;
    private static final int LIQUID_CONTAINER_REGULAR_SIZE = 1000;
    private static final int SMALL_CUP_CONTAINER = 25;
    private static final int BIG_CUP_CONTAINER = 15;
    private static final int PROCESSOR_CONTAINER_SIZE = 500;
    //Other variables
    private final String name = "DisplayDevice";
    private final DeviceType deviceType = DeviceType.Display;
    private JLabel jlabel;
    private JTextArea jTextArea;

    public SwingDisplayDevice() {
        prepareSwing();
    }

    private void prepareSwing() {
        jlabel = new JLabel("Display");
        jlabel.setAlignmentX(LEFT_ALIGNMENT);
        jTextArea = new JTextArea();
        jTextArea.setLineWrap(true);
        jTextArea.setAlignmentX(LEFT_ALIGNMENT);
        BoxLayout boxLayout = new BoxLayout(this, BoxLayout.PAGE_AXIS);
        setLayout(boxLayout);
        add(jlabel);
        add(jTextArea);
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        jTextArea.setEnabled(false);
        setPreferredSize(getMinimumSize());
    }

    @Override
    public String getName() {
        return name;
    }

    public DeviceType getDeviceType() {
        return deviceType;
    }

    @Override
    public void connect(Device device) {

    }

    @Override
    public void disconnect(Device device) {

    }

    @Override
    public void disconnectAll() {

    }

    @Override
    public List<Device> listConnectedDevices() {
        return null;
    }

    @Override
    public void displayMsg(String s) {

    }

    @Override
    public void clear() {

    }
}
