package machine;

import devices.Device;

public interface Machine {
	Device[] listDevices();
	String getModel();
}
