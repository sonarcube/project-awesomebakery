package org.awesomebakery.behaviors;

import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;

public class CheckResponseBehaviour extends Behaviour {
	private static final long serialVersionUID = -6981765604055831573L;
	public boolean done = false;
    private MessageTemplate mt;

    private Behaviour behaviour;

    public CheckResponseBehaviour(Behaviour behaviour, MessageTemplate mt) {
        this.behaviour = behaviour;
        this.mt = mt;
    }

    @Override
    public void action() {
        ACLMessage reply = myAgent.receive(mt);
        if (reply != null) {
            if (reply.getPerformative() == ACLMessage.ACCEPT_PROPOSAL) {
                // Good, nothing to do. exit
                myAgent.doDelete();
            } else {
                // Failed, retry next time.
                System.out.println("failed");
                myAgent.addBehaviour(behaviour);
                if (done()) {
                    myAgent.doDelete();
                }
            }
        } else {
            block();
        }
    }

    @Override
    public boolean done() {
        return done = true;
    }
}
