package org.awesomebakery.agents;

public class KneadingManager extends ManagerAgent {

	private static final long serialVersionUID = -1286719706503574555L;
	public static final String SERVICE_TYPE = "KneadingManager";

	public KneadingManager(String name) {
		super(name);
	}

	@Override
	public String getServiceType() {
		return SERVICE_TYPE;
	}

}
