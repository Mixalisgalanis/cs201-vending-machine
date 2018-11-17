package modules.external;

import devices.external.CoinAcceptorDevice;
import modules.Module;

public class CoinReader extends Module<CoinAcceptorDevice> {

    //Class variables
    private int money;

    //Constructor
    public CoinReader() {
        super("CoinReader");
        money = 0;
    }

    //Other Methods
	public int receiveMoney(int min) {
        //TODO CoinReader - receiveMoney
        while (money < min) {
            money = money + getDevice().acceptCoin(min - money);
        }
        return 0;
	}
}
