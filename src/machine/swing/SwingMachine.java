package machine.swing;

import tuc.ece.cs201.vm.hw.HardwareMachine;
import tuc.ece.cs201.vm.hw.device.Device;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SwingMachine extends JFrame implements HardwareMachine {
    //Container Sizes
    private static final int POWDER_CONTAINER_REGULAR_SIZE = 500;
    private static final int LIQUID_CONTAINER_REGULAR_SIZE = 1000;
    private static final int SMALL_CUP_CONTAINER = 25;
    private static final int BIG_CUP_CONTAINER = 15;
    private static final int PROCESSOR_CONTAINER_SIZE = 500;
    //Constant variables
    static final int FRAME_SIZE_WIDTH = 800;
    static final int FRAME_SIZE_HEIGHT = 600;
    static final double EXTERNAL_INTERNAL_RATIO = 0.3;
    public static final Color ACTIVE_COLOR = Color.lightGray;
    public static final Color DE_ACTIVE_COLOR = Color.white;
    public static final Color BACKGROUND_COLOR = Color.white;
    public static final Color FOREGROUND_COLOR = Color.RED;
    private static final BasicStroke stroke = new BasicStroke(1.0f);
    private static final BasicStroke dStroke = new BasicStroke(2.0f);
    private static final BasicStroke wStroke = new BasicStroke(8.0f);
    //Singleton
    private static SwingMachine instance;
    //Class variables
    private final HashMap<String, Device> devices;
    private ExternalDevices externalDevices;
    private InternalDevices internalDevices;

    //Constructor
    private SwingMachine() {
        devices = new HashMap<>();
        instance = this;

        prepareSwing();
        insertDevices();
    }

    private void prepareSwing() {
        //Swing Preparation
        setSize(FRAME_SIZE_WIDTH, FRAME_SIZE_HEIGHT);
        setResizable(false);
        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Vending Machine");
    }

    public static SwingMachine getInstance() {
        return (instance != null) ? instance : new SwingMachine();
    }

    private void insertDevices() {
        externalDevices = new ExternalDevices();
        internalDevices = new InternalDevices();

        Container container = getContentPane();
        container.add(externalDevices, BorderLayout.LINE_START);
        container.add(internalDevices, BorderLayout.LINE_END);
        pack();
    }

    //Other Methods
    @Override
    public List<Device> listDevices() {
        return new ArrayList<>(devices.values());
    }

    @Override
    public String getModel() {
        return null;
    }


    public void addDevice(Device device) {
        devices.put(device.getName(), device);
    }

    public Device getDevice(String deviceName) {
        return devices.get(deviceName);
    }

    //Other Methods
    public void sleep(int duration) {
        try {
            Thread.sleep(duration * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void refresh() {
        repaint();
    }
}


