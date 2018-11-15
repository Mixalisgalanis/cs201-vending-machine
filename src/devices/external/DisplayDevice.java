package devices.external;

import devices.Device;

public interface DisplayDevice extends Device {

    void displayMsg(String message);

    void clear();
}
