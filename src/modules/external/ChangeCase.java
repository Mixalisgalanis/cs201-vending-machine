package modules.external;

import modules.Module;
import tuc.ece.cs201.vm.hw.device.ChangeCaseDevice;

public class ChangeCase extends Module<ChangeCaseDevice> {

    private final int[] coins = {200, 100, 50, 20, 10, 5, 2, 1};
    private int change;

    //Constructor
    public ChangeCase(ChangeCaseDevice device) {
        super(device);
        setName(getClass().getSimpleName());
        change = 0;
    }

    //OtherMethods
    public void setChange(int change) {
        this.change = change;
        for (int i = 0; i < coins.length; ) {
            if (this.change > coins[i]) {
                getDevice().giveChange(coins[i]);
                this.change -= coins[i];
            } else {
                i += 1;
            }
        }
    }

    public void removeChange() {
        change = 0;
        getDevice().removeChange();
    }
}
