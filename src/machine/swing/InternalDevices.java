package machine.swing;

import devices.swingDevices.internal.SwingDosingContainerDevice;
import devices.swingDevices.internal.SwingFlowContainerDevice;
import devices.swingDevices.internal.SwingMaterialContainerDevice;
import tuc.ece.cs201.vm.hw.device.DeviceType;

import javax.swing.*;
import java.awt.*;

public class InternalDevices extends JPanel {

    private final int gridWidth;
    private final int gridHeight;

    private final SwingDosingContainerDevice coffeeContainerDevice;
    private final SwingDosingContainerDevice sugarContainerDevice;
    private final SwingFlowContainerDevice waterContainerDevice;
    private final SwingFlowContainerDevice milkContainerDevice;
    private final SwingMaterialContainerDevice smallCupContainerDevice;
    private final SwingMaterialContainerDevice bigCupContainerDevice;

    public InternalDevices() {
        setBackground(Color.LIGHT_GRAY);
        Dimension dimension = new Dimension((int) ((1 - SwingMachine.EXTERNAL_INTERNAL_RATIO) * SwingMachine.FRAME_SIZE_WIDTH),
                SwingMachine.FRAME_SIZE_HEIGHT);
        setPreferredSize(dimension);

        gridWidth = dimension.width / 6;
        gridHeight = dimension.height / 4;

        int x = 0;
        int y = 0;

        coffeeContainerDevice = new SwingDosingContainerDevice("CoffeeContainerDevice", DeviceType.DosingContainer,
                SwingMachine.POWDER_CONTAINER_REGULAR_SIZE, (x++) * (gridWidth), y, gridWidth, gridHeight, 10, SwingMachine.COFFEE_COLOR);
        sugarContainerDevice = new SwingDosingContainerDevice("SugarContainerDevice", DeviceType.DosingContainer,
                SwingMachine.POWDER_CONTAINER_REGULAR_SIZE, (x++) * (gridWidth), y, gridWidth, gridHeight, 10, SwingMachine.SUGAR_COLOR);
        waterContainerDevice = new SwingFlowContainerDevice("WaterContainerDevice", DeviceType.FlowContainer,
                SwingMachine.LIQUID_CONTAINER_REGULAR_SIZE, (x++) * (gridWidth), y, gridWidth, gridHeight, 10, SwingMachine.WATER_COLOR);
        milkContainerDevice = new SwingFlowContainerDevice("MilkContainerDevice", DeviceType.FlowContainer,
                SwingMachine.LIQUID_CONTAINER_REGULAR_SIZE, (x++) * (gridWidth), y, gridWidth, gridHeight, 10, SwingMachine.MILK_COLOR);
        smallCupContainerDevice = new SwingMaterialContainerDevice("SmallCupContainerDevice", DeviceType.MaterialContainer,
                SwingMachine.SMALL_CUP_CONTAINER, (x++) * (gridWidth), y, gridWidth, gridHeight, 10, SwingMachine.SMALL_CUP);
        bigCupContainerDevice = new SwingMaterialContainerDevice("BigCupContainerDevice", DeviceType.MaterialContainer,
                SwingMachine.BIG_CUP_CONTAINER, (x++) * (gridWidth), y, gridWidth, gridHeight, 10, SwingMachine.BIG_CUP);
    }

    @Override
    public void paintComponent(Graphics g) {
        // Get the subclass Graphics2D
        Graphics2D g2 = (Graphics2D) g;

        //Draw all Graphic Devices in Internal Devices
        coffeeContainerDevice.draw(g2);
        sugarContainerDevice.draw(g2);
        waterContainerDevice.draw(g2);
        milkContainerDevice.draw(g2);
        smallCupContainerDevice.draw(g2);
        bigCupContainerDevice.draw(g2);

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
