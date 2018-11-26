package modules.external;

import modules.Module;
import tuc.ece.cs201.vm.hw.device.NumPadDevice;

public class NumPad extends Module<NumPadDevice> {

    //Constructor
    public NumPad(NumPadDevice device) {
        super("NumPad", device);
    }

    //Other Methods
    public int readCode(int length) {
        //TODO
        int code = 0;
        for (int i = 0; i < length; i++) code += 10 ^ (i) * getDevice().readDigit();
        return code;
    }
}
