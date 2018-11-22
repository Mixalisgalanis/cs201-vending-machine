package devices.consoleDevices.external;

import devices.external.CoinAcceptorDevice;

public class ConsoleCoinAcceptorDevice extends ConsoleLockableExternalDevice implements CoinAcceptorDevice {

    public ConsoleCoinAcceptorDevice() {
        super ("CoinAcceptor");
    }

    @Override
    public int acceptCoin(int supTotal) {
        if (supTotal > coin){
            return coin;
        }

    }
}