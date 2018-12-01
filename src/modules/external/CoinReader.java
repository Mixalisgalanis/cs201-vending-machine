package modules.external;


import modules.Module;
import tuc.ece.cs201.vm.hw.device.CoinAcceptorDevice;

public class CoinReader extends Module<CoinAcceptorDevice> {

    //Class variables
    private int money;

    //Constructor
    public CoinReader(CoinAcceptorDevice device) {
        super(device);
        setName(getClass().getSimpleName());
        money = 0;
    }

    //Other Methods
    public int receiveMoney(int min) {
        while (money < min) {
            money += getDevice().acceptCoin(min - money);
        }
        return money - min;
    }

    public void clearMoney() {
        money = 0;
    }
}
