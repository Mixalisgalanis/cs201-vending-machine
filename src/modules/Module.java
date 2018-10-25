package modules;

abstract public class Module {
	private String name;
	
	public Module (String name) {
		this.name=name;
	}
	
	public abstract String getName();
}
