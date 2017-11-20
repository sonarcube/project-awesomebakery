package org.awesomebakery.utils;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import jade.core.behaviours.OneShotBehaviour;
import jade.domain.DFService;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.domain.FIPAException;
import org.awesomebakery.agents.Bakery;
import org.awesomebakery.agents.Customer;
import org.awesomebakery.agents.KneadingManager;
import org.awesomebakery.model.Order;
import org.awesomebakery.model.Product;

import java.util.ArrayList;
import java.util.List;

public class FindAgentBehaviour extends Behaviour {
    private String agent;
    private List<AID> agentID = new ArrayList<>();
    private List<AID> foundedAgentID;
    private List<Object> objectList;
    private OneShotBehaviour behaviour;

    public List<AID> getFoundedAgentID() {
        return foundedAgentID;
    }

    public void setFoundedAgentID(List<AID> foundedAgentID) {
        this.foundedAgentID = foundedAgentID;
    }

    public FindAgentBehaviour(String agent, List<Object> objectList, OneShotBehaviour behaviour) {
        this.agent = agent;
        this.objectList = objectList;
        this.behaviour = behaviour;
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
                agentID.add(d.getName());
            }
        } catch (FIPAException fe) {
            fe.printStackTrace();
        }

        setFoundedAgentID(agentID);
        if (!agentID.isEmpty()) {
            /*for (Object object : objectList) {
                myAgent.addBehaviour(behaviour);
            }*/

            myAgent.addBehaviour(behaviour);
        }

    }

    @Override
    public boolean done() {
        return !agentID.isEmpty();
    }
}
