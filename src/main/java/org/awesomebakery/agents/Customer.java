package org.awesomebakery.agents;

import java.util.List;

import jade.core.behaviours.OneShotBehaviour;
import jade.core.behaviours.WakerBehaviour;
import org.awesomebakery.behaviors.FindAgentBehaviour;
import org.awesomebakery.model.Date;
import org.awesomebakery.model.Order;

import jade.core.AID;
import jade.core.Agent;
import jade.lang.acl.ACLMessage;

public class Customer extends Agent {

	private static final long serialVersionUID = 2900621781398066801L;
	private final List<Order> orders;
	private List<AID> bakeries;

	public Customer(List<Order> orders) {
		this.orders = orders;
	}

	protected void setup() {
		addBehaviour(new FindAgentBehaviour(Bakery.SERVICE_TYPE, (List<AID> foundAgents) -> {
			bakeries = foundAgents;
			if (orders != null) {
				for (Order order : orders) {
					addBehaviour(new WakerBehaviour(this, setTimetoOrder(order)) {
						@Override
						protected void onWake() {
							super.onWake();
							addBehaviour(new PlaceOrderBehaviour(order));
						}
					});
				}
			}
		}));
	}
	private long setTimetoOrder(Order order){
		return order.getOrderDate().getDay()*2400+order.getOrderDate().getHour()*600;
	}
	private boolean checkDeliveryTime(Order order, long bakeryAnswer){

		long hour = bakeryAnswer-order.getOrderDate().getHour()*600;
		hour = hour - order.getOrderDate().getDay()*2400;

		long day = bakeryAnswer-hour;

		if (hour == order.getOrderDate().getDay()*2400 && ( day<= (order.getOrderDate().getDay()+200))){
			return true;
		}
		else{
			return false;
		}

	}
	private class PlaceOrderBehaviour extends OneShotBehaviour {

		private static final long serialVersionUID = 4800096756945729705L;
		private Order order;

		PlaceOrderBehaviour(Order order) {
			this.order = order;
		}

		@Override
		public void action() {
			ACLMessage cfp = new ACLMessage(ACLMessage.CFP);
			for (AID bakery : bakeries) {
				cfp.addReceiver(bakery);
			}
			cfp.setContent(order.toString());
			cfp.setConversationId("request-price-for" + order.getGuid());
			cfp.setReplyWith("cfp" + System.currentTimeMillis());
			myAgent.send(cfp);
		}
	}
}
