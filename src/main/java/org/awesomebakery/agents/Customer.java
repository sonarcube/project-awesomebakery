package org.awesomebakery.agents;

import java.util.List;
import java.util.Vector;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import jade.core.behaviours.OneShotBehaviour;
import jade.domain.DFService;
import jade.domain.FIPAException;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;

public class Customer extends Agent {

	private static final long serialVersionUID = 1L;
	private List<AID> cashiers = new Vector<>();

	protected void setup() {
		DFAgentDescription dfd = new DFAgentDescription();
		dfd.setName(getAID());
		ServiceDescription sd = new ServiceDescription();
		sd.setType("customer");
		sd.setName("customer");
		dfd.addServices(sd);
		try {
			DFService.register(this, dfd);
		} catch (FIPAException e) {
			// TODO handle
			e.printStackTrace();
		}
		addBehaviour(new FindCashiersBehaviour());
	}

	private class FindCashiersBehaviour extends Behaviour {
		private static final long serialVersionUID = 1L;

		@Override
		public void action() {
			DFAgentDescription template = new DFAgentDescription();
			ServiceDescription sd = new ServiceDescription();
			sd.setType("cashier");
			template.addServices(sd);
			try {
				cashiers.clear();
				DFAgentDescription[] result = DFService.search(myAgent, template);
				for (DFAgentDescription d : result) {
					cashiers.add(d.getName());
				}
			} catch (FIPAException fe) {
				fe.printStackTrace();
			}
			if (!cashiers.isEmpty()) {
				myAgent.addBehaviour(new PlaceOrderBehaviour());
			}

		}

		@Override
		public boolean done() {
			// TODO Auto-generated method stub
			return !cashiers.isEmpty();
		}

	}

	private MessageTemplate mt = null;

	private class PlaceOrderBehaviour extends OneShotBehaviour {
		private static final long serialVersionUID = 1L;

		@Override
		public void action() {
			ACLMessage cfp = new ACLMessage(ACLMessage.CFP);
			cfp.addReceiver(cashiers.get(0));
			cfp.setContent("test1");
			cfp.setConversationId("order");
			cfp.setReplyWith("cfp" + System.currentTimeMillis());
			myAgent.send(cfp);
			mt = MessageTemplate.and(MessageTemplate.MatchConversationId(cfp.getConversationId()),
					MessageTemplate.MatchInReplyTo(cfp.getReplyWith()));
			myAgent.addBehaviour(new CheckResponseBehaviour());
		}

	}

	private class CheckResponseBehaviour extends Behaviour {
		private static final long serialVersionUID = 1L;
		boolean done = false;
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
					myAgent.addBehaviour(new FindCashiersBehaviour());
					done = true;
				}
			} else {
				block();
			}
		}

		@Override
		public boolean done() {
			return done;
		}

	}
}
