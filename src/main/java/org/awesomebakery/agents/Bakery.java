package org.awesomebakery.agents;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import jade.core.behaviours.CyclicBehaviour;
import jade.core.behaviours.OneShotBehaviour;
import jade.domain.DFService;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.domain.FIPAException;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import org.awesomebakery.model.Location;
import org.awesomebakery.model.Order;
import org.awesomebakery.model.Product;
import org.awesomebakery.utils.CheckResponseBehaviour;
import org.awesomebakery.utils.FindAgentBehaviour;

import java.util.List;
import java.util.Vector;

public class Bakery extends Agent {

	public static final String SERVICE_TYPE = "bakery";
	
	private List<String> orders = new Vector<>();
	private List<Object> products;
	private String name;
	//create a new instance for KneadingManager
	private FindAgentBehaviour findingKneadingManager = new FindAgentBehaviour("KneadingManager", products, new PlaceOrderKneadingBehaviour());
	private FindAgentBehaviour findingOvenManager = new FindAgentBehaviour("OvenManager", products, new PlaceOrderOvenBehaviour());
	private FindAgentBehaviour findingDoughPreparer = new FindAgentBehaviour("DoughPreparer", products, new PlaceOrderPrepareBehaviour());
	private FindAgentBehaviour findingDoughRester = new FindAgentBehaviour("DoughRester", products, new PlaceOrderRestBehaviour());


	public Bakery(String name) {
		this.name = name;
	}

	protected void setup() {
		DFAgentDescription dfd = new DFAgentDescription();
		dfd.setName(getAID());
		ServiceDescription sd = new ServiceDescription();
		sd.setType(SERVICE_TYPE);
		sd.setName(name);
		dfd.addServices(sd);
		try {
			DFService.register(this, dfd);
		} catch (FIPAException e) {
			// TODO handle
			e.printStackTrace();
		}
		addBehaviour(new Bakery.TakeOrder());
		//add a new Behaviour for the KneadingManager
		addBehaviour(findingKneadingManager);
		addBehaviour(findingOvenManager);
		addBehaviour(findingDoughPreparer);
		addBehaviour(findingDoughRester);

	}

	private class TakeOrder extends CyclicBehaviour {
		private static final long serialVersionUID = 1L;

		@Override
		public void action() {
			MessageTemplate mt = MessageTemplate.MatchPerformative(ACLMessage.CFP);
			ACLMessage msg = myAgent.receive(mt);
			if (msg != null) {
				String name = msg.getContent();
				ACLMessage reply = msg.createReply();
				orders.add(name);
				reply.setPerformative(ACLMessage.ACCEPT_PROPOSAL);
				myAgent.send(reply);
				System.out.println("I got this Order "+orders);
			} else {
				block();
			}
		}
	}

	private class PlaceOrderKneadingBehaviour extends OneShotBehaviour {
		private static final long serialVersionUID = 1L;
		private MessageTemplate mt = null;


		@Override
		public void action() {
			ACLMessage cfp = new ACLMessage(ACLMessage.CFP);
			for (AID kneadingManager : findingKneadingManager.getFoundedAgentID()) {
				cfp.addReceiver(kneadingManager);
			}
			cfp.setContent("Test Massage");
			cfp.setConversationId("TestID");
			cfp.setReplyWith("cfp" + System.currentTimeMillis());
			myAgent.send(cfp);
			mt = MessageTemplate.and(MessageTemplate.MatchConversationId(cfp.getConversationId()),
					MessageTemplate.MatchInReplyTo(cfp.getReplyWith()));
			myAgent.addBehaviour(new CheckResponseBehaviour(findingKneadingManager, mt));
		}

	}
	private class PlaceOrderOvenBehaviour extends OneShotBehaviour {
		private static final long serialVersionUID = 1L;
		private MessageTemplate mt = null;


		@Override
		public void action() {
			ACLMessage cfp = new ACLMessage(ACLMessage.CFP);
			for (AID kneadingManager : findingOvenManager.getFoundedAgentID()) {
				cfp.addReceiver(kneadingManager);
			}
			cfp.setContent("Test Massage");
			cfp.setConversationId("TestID");
			cfp.setReplyWith("cfp" + System.currentTimeMillis());
			myAgent.send(cfp);
			mt = MessageTemplate.and(MessageTemplate.MatchConversationId(cfp.getConversationId()),
					MessageTemplate.MatchInReplyTo(cfp.getReplyWith()));
			myAgent.addBehaviour(new CheckResponseBehaviour(findingOvenManager, mt));
		}

	}
	private class PlaceOrderPrepareBehaviour extends OneShotBehaviour {
		private static final long serialVersionUID = 1L;
		private MessageTemplate mt = null;


		@Override
		public void action() {
			ACLMessage cfp = new ACLMessage(ACLMessage.CFP);
			for (AID kneadingManager : findingDoughPreparer.getFoundedAgentID()) {
				cfp.addReceiver(kneadingManager);
			}
			cfp.setContent("Test Massage");
			cfp.setConversationId("TestID");
			cfp.setReplyWith("cfp" + System.currentTimeMillis());
			myAgent.send(cfp);
			mt = MessageTemplate.and(MessageTemplate.MatchConversationId(cfp.getConversationId()),
					MessageTemplate.MatchInReplyTo(cfp.getReplyWith()));
			myAgent.addBehaviour(new CheckResponseBehaviour(findingDoughPreparer, mt));
		}

	}
	private class PlaceOrderRestBehaviour extends OneShotBehaviour {
		private static final long serialVersionUID = 1L;
		private MessageTemplate mt = null;


		@Override
		public void action() {
			ACLMessage cfp = new ACLMessage(ACLMessage.CFP);
			for (AID kneadingManager : findingDoughRester.getFoundedAgentID()) {
				cfp.addReceiver(kneadingManager);
			}
			cfp.setContent("Test Massage");
			cfp.setConversationId("TestID");
			cfp.setReplyWith("cfp" + System.currentTimeMillis());
			myAgent.send(cfp);
			mt = MessageTemplate.and(MessageTemplate.MatchConversationId(cfp.getConversationId()),
					MessageTemplate.MatchInReplyTo(cfp.getReplyWith()));
			myAgent.addBehaviour(new CheckResponseBehaviour(findingDoughRester, mt));
		}

	}
}
