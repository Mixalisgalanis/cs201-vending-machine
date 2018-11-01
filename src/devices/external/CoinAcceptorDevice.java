package devices.external;

import behaviour.Lockable;

public interface CoinAcceptorDevice extends Lockable{

	int acceptCoin(int supTotal);
}
