package devices.external;

import behaviour.Lockable;

public interface ChangeCaseDevice extends Lockable {

    void giveChange(int coin);

    void removeChange();
}
