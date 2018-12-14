package devices.swingDevices;

import machine.swing.SwingMachine;
import tuc.ece.cs201.vm.hw.device.Device;
import tuc.ece.cs201.vm.hw.device.DeviceType;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public abstract class SwingDevice implements Device {

    //Class variables
    final static int maxCharHeight = 15;
    final static int minFontSize = 6;
    private static ArrayList<Device> connectedDevices;
    private final String name;
    private final DeviceType deviceType;
    protected final int x;
    protected final int y;
    protected final int width;
    protected final int height;
    protected final int padding;
    boolean connected;
    private boolean activated;

    //Constructor
    public SwingDevice(String name, DeviceType deviceType, int x, int y, int width, int height, int padding) {
        this.name = name;
        this.deviceType = deviceType;
        this.padding = padding;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    //Methods
    public void activate() {
        activated = true;
        //TODO Insert Graphics Activation
        SwingMachine.getInstance().refresh();
    }


    public void deActivate() {
        activated = false;
        //TODO Insert Graphics Activation
        SwingMachine.getInstance().refresh();
    }

    public boolean isActive() {
        return activated;
    }


    @Override
    public String getName() {
        return name;
    }

    @Override
    public void connect(Device device) {
        if (!connected) {
            connected = true;
            //TODO Insert Graphics Connection
            SwingMachine.getInstance().refresh();
            device.connect(this);
            connectedDevices.add(device);
        }
    }

    @Override
    public void disconnect(Device device) {
        if (connected) {
            connected = false;
            //TODO Insert Graphics Connection
            SwingMachine.getInstance().refresh();
            device.disconnect(this);
            connectedDevices.remove(device);
        }
    }

    @Override
    public void disconnectAll() {
        connectedDevices.clear();
    }

    @Override
    public List<Device> listConnectedDevices() {
        return connectedDevices;
    }

    @Override
    public DeviceType getType() {
        return deviceType;
    }

    FontMetrics pickFont(Graphics2D g2, String longString, int xSpace) {
        boolean fontFits = false;
        Font font = g2.getFont();
        FontMetrics fontMetrics = g2.getFontMetrics();
        int size = font.getSize();
        String name = font.getName();
        int style = font.getStyle();

        while (!fontFits) {
            if ((fontMetrics.getHeight() <= maxCharHeight) &&
                    (fontMetrics.stringWidth(longString) <= xSpace)) {
                fontFits = true;
            } else {
                if (size <= minFontSize) {
                    fontFits = true;
                } else {
                    g2.setFont(font = new Font(name, style, --size));
                    fontMetrics = g2.getFontMetrics();
                }
            }
        }

        return fontMetrics;
    }

    public abstract void draw(Graphics g);
}
