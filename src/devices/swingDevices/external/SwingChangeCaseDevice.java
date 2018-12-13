package devices.swingDevices.external;

import tuc.ece.cs201.vm.hw.device.ChangeCaseDevice;
import tuc.ece.cs201.vm.hw.device.Device;
import tuc.ece.cs201.vm.hw.device.DeviceType;

import javax.swing.*;
import java.util.List;

public class SwingChangeCaseDevice extends JPanel implements ChangeCaseDevice {

    //Class variables
    private final String name = "ChangeCaseDevice";
    private final DeviceType deviceType = DeviceType.ChangeCase;
    private JLabel jlabel;
    private JTextField jTextField;

    //Constructor
    public SwingChangeCaseDevice() {
        prepareSwing();
    }

    private void prepareSwing() {
        jlabel = new JLabel("Change Case");
        jlabel.setAlignmentX(LEFT_ALIGNMENT);
        jTextField = new JTextField();
        jTextField.setAlignmentX(LEFT_ALIGNMENT);
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        add(jlabel);
        add(jTextField);
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        jTextField.setEnabled(false);
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
    public void lock() {

    }

    @Override
    public void unLock() {

    }

    @Override
    public boolean isLocked() {
        return false;
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
    public void giveChange(int i) {

    }

    @Override
    public void removeChange() {

    }
}
