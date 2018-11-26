package devices.consoleDevices.external;

import tuc.ece.cs201.vm.hw.device.CoinAcceptorDevice;
import tuc.ece.cs201.vm.hw.device.DeviceType;
import utilities.Reader;

public class ConsoleCoinAcceptorDevice extends ConsoleLockableExternalDevice implements CoinAcceptorDevice {

    private Reader reader;

    public ConsoleCoinAcceptorDevice() {
        super("CoinAcceptor", DeviceType.CoinReader);
        reader = new Reader();
    }


    @Override
    public int acceptCoin(int supTotal) {
        int coin = reader.readInt("Insert coin");
        if (supTotal > coin) {
            return coin;
        }
        return 0;
    }
}