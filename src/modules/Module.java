package modules;

import devices.Device;

abstract public class Module<T extends Device> {
	private String name;
	private T device;
	
	public Module (String name) {
		this.name=name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
