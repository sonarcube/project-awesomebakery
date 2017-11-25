package org.awesomebakery.agents.managers;

public class BoxingManager extends ManagerAgent {

    private static final long serialVersionUID = -6759979796386187837L;
    public static final String SERVICE_TYPE = "BoxingManager";

    public BoxingManager(String name) {
        super(name);
    }

    @Override
    public String getServiceType() {
        return SERVICE_TYPE;
    }

}
