package devices.consoleDevices.external;

import tuc.ece.cs201.vm.hw.device.CoinAcceptorDevice;
import tuc.ece.cs201.vm.hw.device.DeviceType;
import utilities.Reader;

import java.util.ArrayList;
import java.util.Arrays;

public class ConsoleCoinAcceptorDevice extends ConsoleLockableExternalDevice implements CoinAcceptorDevice {

    private final ArrayList<Integer> supportedCoins = new ArrayList<>(Arrays.asList(new Integer[]{1, 2, 5, 10, 20, 50, 100, 200
    }));

    public ConsoleCoinAcceptorDevice() {
        super("CoinAcceptorDevice", DeviceType.CoinReader);
    }

    @Override
    public int acceptCoin(int supTotal) {
        int coin = Reader.readInt("Insert coin (" + supTotal + "c remaining): ");
        while (!supportedCoins.contains(coin)) {
            coin = Reader.readInt("Invalid Coin! Try again ( " + supTotal + "c remaining): ");
        }
        return coin;
    }
}