package modules.external;

import devices.external.NumPadDevice;
import modules.Module;

public class NumPad extends Module<NumPadDevice> {

    //Constructor
    public NumPad() {
        super("NumPad");
    }

    //Other Methods
    public int readCode(int length) {
        int code = 0;
        for (int i = 0; i < length; i++) code += 10 ^ (i) * getDevice().readDigit();
        return code;
    }
}
