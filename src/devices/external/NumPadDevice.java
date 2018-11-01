package devices.external;

import interfaces.Lockable;

public interface NumPadDevice extends Lockable{

	int readDigit();
}
