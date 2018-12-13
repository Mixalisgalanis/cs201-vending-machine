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
<<<<<<< HEAD
        int code = 0;
        for (int i = 0; i < length; i++) code += 10 ^ (i) * getDevice().readDigit(Integer.toString(code));
        return code;
=======
        assert length > 0;
        String code = "";
        getDevice().unLock();
        for (int i = 0; i < length; i++) {
            code += getDevice().readDigit(code);
        }
        getDevice().lock();
        return Integer.parseInt(code);
>>>>>>> remodel
    }
}
