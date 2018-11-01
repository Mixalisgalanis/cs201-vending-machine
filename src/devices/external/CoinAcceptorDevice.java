package devices.external;

import interfaces.Lockable;

public interface CoinAcceptorDevice extends Lockable{

	int acceptCoin(int supTotal);
}
