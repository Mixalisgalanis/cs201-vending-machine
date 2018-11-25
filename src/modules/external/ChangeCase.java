package modules.external;

import devices.external.ChangeCaseDevice;
import modules.Module;

import java.util.ArrayList;
import java.util.Arrays;

public class ChangeCase extends Module<ChangeCaseDevice> {

    int change;
    private ArrayList<Integer> coins;

    //Constructor
    public ChangeCase() {
        super("ChangeCase");
        this.change = 0;
        coins = new ArrayList<Integer>(Arrays.asList(200, 100, 50, 20, 10));
    }

    //OtherMethods
    public void setChange(int change) {
        this.change = change;
        for (int i = 0; i < coins.size(); ) {
            if (change > coins.get(i)) {
                change -= coins.get(i);
                getDevice().giveChange(coins.get(i));
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
