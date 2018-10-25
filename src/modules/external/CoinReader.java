package modules.external;

import modules.Module;

public class CoinReader extends Module {
	//private String name;
	private int money;
	
	public CoinReader() {
		super ("coinReader");
		money = 0;
	}
	
	public String getName() {
		return "coinReader";
	}
}
