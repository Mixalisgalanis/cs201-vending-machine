package devices.consoleDevices.external;

import devices.external.CoinAcceptorDevice;

public class ConsoleCoinAcceptorDevice extends ConsoleLockableExternalDevice implements CoinAcceptorDevice {
    @Override
    public int acceptCoin(int supTotal) {
        return 0;
    }
}