package modules.external;

import devices.external.ChangeCaseDevice;
import modules.Module;

public class ChangeCase extends Module<ChangeCaseDevice> {

    private int change;
    private final int[] coins = {200, 100, 50, 20, 10, 5, 2, 1};

    //Constructor
    public ChangeCase(ChangeCaseDevice device) {
        super("ChangeCase", device);
        this.change = 0;
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
        this.change = 0;
        getDevice().removeChange();
    }
}
