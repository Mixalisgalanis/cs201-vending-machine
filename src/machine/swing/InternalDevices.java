package machine.swing;

import devices.swingDevices.internal.SwingContainerDevice;
import devices.swingDevices.internal.SwingDosingContainerDevice;
import tuc.ece.cs201.vm.hw.device.DeviceType;

import javax.swing.*;
import java.awt.*;

public class InternalDevices extends JPanel {

    private final int gridWidth;
    private final int gridHeight;

    private final SwingContainerDevice cont1;
    private final SwingContainerDevice cont2;

    public InternalDevices() {
        setBackground(Color.LIGHT_GRAY);
        Dimension dimension = new Dimension((int) ((1 - SwingMachine.EXTERNAL_INTERNAL_RATIO) * SwingMachine.FRAME_SIZE_WIDTH),
                SwingMachine.FRAME_SIZE_HEIGHT);
        setPreferredSize(dimension);

        gridWidth = dimension.width / 6;
        gridHeight = dimension.height / 4;

        cont1 = new SwingDosingContainerDevice("CoffeeContainer", DeviceType.DosingContainer, 100, 0, 0, gridWidth,
                gridHeight, 10,
                SwingMachine.COFFEE_COLOR);
        SwingMachine.getInstance().addDevice(cont1);

        cont2 = new SwingDosingContainerDevice("SugarContainer", DeviceType.DosingContainer, 100, gridWidth, 0,
                gridWidth, gridHeight, 10,
                SwingMachine.SUGAR_COLOR);
        SwingMachine.getInstance().addDevice(cont2);
    }

    @Override
    public void paintComponent(Graphics g) {
        // Get the subclass Graphics2D
        Graphics2D g2 = (Graphics2D) g;

        //Draw all Graphic Devices in Internal Devices
        cont1.draw(g2);
        cont2.draw(g2);

        // Draw the grid with dashes lines
        g2.setStroke(SwingMachine.dashed);

        for (int h = gridHeight; h < getSize().getHeight(); h += gridHeight) {
            g.drawLine(0, h, (int) getSize().getWidth(), h);
        }
        for (int w = gridWidth; w < getSize().getWidth(); w += gridWidth) {
            g.drawLine(w, 0, w, (int) getSize().getHeight());
        }

        g2.setStroke(SwingMachine.stroke);

    }
}
