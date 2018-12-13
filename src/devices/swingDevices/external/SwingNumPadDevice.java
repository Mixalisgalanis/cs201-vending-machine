package devices.swingDevices.external;

import machine.swing.SwingMachine;
import tuc.ece.cs201.vm.hw.device.Device;
import tuc.ece.cs201.vm.hw.device.DeviceType;
import tuc.ece.cs201.vm.hw.device.NumPadDevice;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class SwingNumPadDevice extends JPanel implements NumPadDevice, ActionListener {

    private final String name = "NumPadDevice";
    private final DeviceType deviceType = DeviceType.NumPad;
    private ButtonArray jnumpad;
    private JLabel jlabel;

    private int keyPressed = -1;
    private boolean active;

    public SwingNumPadDevice() {
        prepareSwing();
    }

    private void prepareSwing() {
        jlabel = new JLabel("Numpad");
        jlabel.setAlignmentX(LEFT_ALIGNMENT);
        jnumpad = new ButtonArray(this);
        jnumpad.setAlignmentX(LEFT_ALIGNMENT);
        BoxLayout boxLayout = new BoxLayout(this, BoxLayout.PAGE_AXIS);
        setLayout(boxLayout);
        add(jlabel);
        add(jnumpad);
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        jnumpad.setEnabled(false);
        setPreferredSize(getMinimumSize());
    }

    @Override
    public void lock() {
        setBackground(SwingMachine.DE_ACTIVE_COLOR);
        jnumpad.setEnabled(false);
        active = false;
    }


    @Override
    public void unLock() {
        setBackground(SwingMachine.ACTIVE_COLOR);
        jnumpad.setEnabled(true);
        active = true;
    }

    @Override
    public boolean isLocked() {
        return false;
    }

    @Override
    public String getName() {
        return null;
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
    public synchronized int readDigit(String c) {
        keyPressed = -1;
        try {
            wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return keyPressed;
    }

    @Override
    public synchronized void actionPerformed(ActionEvent e) {
        try {
            keyPressed = Integer.parseInt(e.getActionCommand());
        } catch (NumberFormatException ex) {
            keyPressed = -1;
        }
        notify();
    }

    private class ButtonArray extends JPanel {
        JButton[] b = new JButton[10];

        ButtonArray(ActionListener al) {
            GridLayout gl = new GridLayout(0, 3);
            setLayout(gl);
            for (int i = 1; i < 10; i++) {
                b[i] = new JButton("" + i);
                add(b[i]);
                b[i].addActionListener(al);
            }
            b[0] = new JButton("0");
            add(b[0]);
            b[0].addActionListener(al);
        }

        @Override
        public void setEnabled(boolean st) {
            for (int i = 0; i < getComponentCount(); i++) {
                getComponent(i).setEnabled(st);
            }
        }
    }
}

