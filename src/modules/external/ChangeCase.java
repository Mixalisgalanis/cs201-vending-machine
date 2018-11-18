package modules.external;

import devices.external.ChangeCaseDevice;
import modules.Module;

public class ChangeCase extends Module<ChangeCaseDevice> {

    int change;

    //Constructor
    public ChangeCase() {
        super("ChangeCase");
        this.change = 0;
    }

    //OtherMethods
    public void setChange(int change) {
        this.change = change;
    }

    public void removeChange() {
        this.change = 0;
    }
}
