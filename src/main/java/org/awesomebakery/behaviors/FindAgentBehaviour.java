package org.awesomebakery.behaviors;

import java.util.ArrayList;
import java.util.List;

import jade.core.AID;
import jade.core.behaviours.Behaviour;
import jade.domain.DFService;
import jade.domain.FIPAException;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;

public class FindAgentBehaviour extends Behaviour {
	private static final long serialVersionUID = 8474898985378162700L;
	private String agent;
    private List<AID> foundedAgentIDs = new ArrayList<>();
    private FoundAgentsListener foundAgentsListener;
    
    public interface FoundAgentsListener {
    	public void onFoundAgents(List<AID> agents);
    }

    public FindAgentBehaviour(String agent, FoundAgentsListener foundAgentsListener) {
        this.agent = agent;
        this.foundAgentsListener = foundAgentsListener;
    }

    @Override
    public void action() {
        DFAgentDescription template = new DFAgentDescription();
        ServiceDescription sd = new ServiceDescription();
        sd.setType(agent);
        template.addServices(sd);
        try {
            DFAgentDescription[] result = DFService.search(myAgent, template);
            for (DFAgentDescription d : result) {
                foundedAgentIDs.add(d.getName());
            }
        } catch (FIPAException fe) {
            fe.printStackTrace();
        }
        if (!foundedAgentIDs.isEmpty()) {
            foundAgentsListener.onFoundAgents(foundedAgentIDs);
        }

    }

    @Override
    public boolean done() {
        return !foundedAgentIDs.isEmpty();
    }
}
