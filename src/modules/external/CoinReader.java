package modules.external;

import devices.external.CoinAcceptorDevice;
import modules.Module;

public class CoinReader extends Module<CoinAcceptorDevice> {

    //Class variables
    private int money;

    //Constructor
    public CoinReader() {
        super("Coin Reader");
        money = 0;
    }

    //Other Methods
	public int receiveMoney(int min) {
        //TODO CoinReader - receiveMoney
        return 0;
	}
}
