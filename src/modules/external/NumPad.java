package modules.external;

import modules.Module;
import tuc.ece.cs201.vm.hw.device.NumPadDevice;

public class NumPad extends Module<NumPadDevice> {

    //Constructor
    public NumPad(NumPadDevice device) {
        super(device);
        setName(getClass().getSimpleName());
    }

    //Other Methods
    public int readCode(int length) {
        assert length > 0;
        String code = "";
        for (int i = 0; i < length; i++) {
            code += getDevice().readDigit(code);
        }
        return Integer.parseInt(code);
    }
}
