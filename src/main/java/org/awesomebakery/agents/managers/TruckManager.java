package org.awesomebakery.agents.managers;

public class TruckManager extends ManagerAgent {

	private static final long serialVersionUID = -2559979356346187822L;
	public static final String SERVICE_TYPE = "TruckManager";

	public TruckManager(String name) {
		super(name);
	}

	@Override
	public String getServiceType() {
		return SERVICE_TYPE;
	}

}
