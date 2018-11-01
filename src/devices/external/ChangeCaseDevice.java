package devices.external;

import interfaces.Lockable;

public interface ChangeCaseDevice extends Lockable{
	
	void giveChange(int coin);
	
	void removeChange();
}
