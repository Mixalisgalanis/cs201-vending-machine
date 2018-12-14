package devices.swingDevices.internal;

import devices.swingDevices.SwingDevice;
import machine.swing.SwingMachine;
import tuc.ece.cs201.vm.hw.device.ContainerDevice;
import tuc.ece.cs201.vm.hw.device.DeviceType;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;

public class SwingContainerDevice extends SwingDevice implements ContainerDevice {

    private boolean opened;
    private final int capacity;
    private final Color color;
    private int currentQuantity;

    public SwingContainerDevice(String name, DeviceType deviceType, int capacity, int x, int y, int width, int height,
                                int border, Color color) {
        super(name, deviceType, x, y, width, height, border);

        this.color = color;
        this.capacity = capacity;
        currentQuantity = capacity;

    }


    @Override
    public int getCapacity() {
        return 0;
    }

    @Override
    public void open() {

    }

    @Override
    public void close() {

    }

    @Override
    public boolean isOpen() {
        return false;
    }

    public void decreaseContent(int size) {
        currentQuantity -= size;
        if (currentQuantity < 0) {
            currentQuantity = 0;
        }
        SwingMachine.getInstance().refresh();
    }

    public void increaseContent(int size) {
        currentQuantity += size;
        if (currentQuantity > capacity) {
            currentQuantity = capacity;
        }
        SwingMachine.getInstance().refresh();
    }

    @Override
    public void draw(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        FontMetrics fontMetrics = g2.getFontMetrics();//pickFont(g2, name,width);
        int rectWidth = width - 2 * padding;
        int labely = height - fontMetrics.getDescent() - 3;
        int rectHeight = labely - fontMetrics.getMaxAscent() - padding;

        if (isActive()) {
            g2.setPaint(SwingMachine.ACTIVE_COLOR);
            g2.setStroke(SwingMachine.wStroke);
        }

        g2.draw(new Rectangle2D.Double(x + padding, y + padding, rectWidth, rectHeight));
        g2.setPaint(color);
        double fillPercent = (double) currentQuantity / (double) capacity;
        g2.fill(new RoundRectangle2D.Double(x + padding + 5, y + padding + (rectHeight * (1 - fillPercent)) + 5, rectWidth - 10, rectHeight * fillPercent - 10, 10, 10));

        g2.setPaint(SwingMachine.FOREGROUND_COLOR);
        g2.setStroke(SwingMachine.stroke);

        g2.drawString(getName(), x + padding, y + labely);
    }
}
