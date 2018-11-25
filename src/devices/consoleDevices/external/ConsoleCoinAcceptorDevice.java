package devices.consoleDevices.external;

import devices.external.CoinAcceptorDevice;
import utilities.Reader;

public class ConsoleCoinAcceptorDevice extends ConsoleLockableExternalDevice implements CoinAcceptorDevice {

    private Reader reader;

    public ConsoleCoinAcceptorDevice() {
        super("CoinAcceptor");
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