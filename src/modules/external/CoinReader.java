package modules.external;

import devices.external.CoinAcceptorDevice;
import modules.Module;

public class CoinReader extends Module<CoinAcceptorDevice> {

    //Class variables
    private int money;

    //Constructor
    public CoinReader(CoinAcceptorDevice device) {
        super("CoinReader", device);
        this.money = 0;
    }

    //Other Methods
    public int receiveMoney(int min) {
        while (money < min) {
            money += getDevice().acceptCoin(min - money);
        }
        return money - min;
    }

    public void clearMoney() {
        this.money = 0;
    }
}
