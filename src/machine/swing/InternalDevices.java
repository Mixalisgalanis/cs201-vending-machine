package machine.swing;

import javax.swing.*;
import java.awt.*;

public class InternalDevices extends JPanel {

    private final int gridWidth;
    private final int gridHeight;

    public InternalDevices() {
        setBackground(Color.LIGHT_GRAY);
        Dimension dimension = new Dimension((int) ((1 - SwingMachine.EXTERNAL_INTERNAL_RATIO) * SwingMachine.FRAME_SIZE_WIDTH),
                SwingMachine.FRAME_SIZE_HEIGHT);
        setPreferredSize(dimension);

        gridWidth = dimension.width / 6;
        gridHeight = dimension.height / 4;
    }
}
