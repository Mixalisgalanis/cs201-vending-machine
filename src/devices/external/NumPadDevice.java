package devices.external;

import behaviour.Lockable;

public interface NumPadDevice extends Lockable{

	int readDigit();
}
