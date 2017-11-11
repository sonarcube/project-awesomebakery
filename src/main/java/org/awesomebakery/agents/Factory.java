package org.awesomebakery.agents;

import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import jade.core.behaviours.CyclicBehaviour;
import jade.domain.DFService;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.domain.FIPAException;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import org.awesomebakery.model.Location;

import java.util.List;
import java.util.Vector;

public class Factory extends Agent {

	/*private Location location;
	private int numberDoughKneadingMachines;
	private int numberOvens;
	private int ovenHeatingTimeFactor;
	private int ovenCoolingTimeFactor;

	public Factory(Location location, int numberDoughKneadingMachines, int numberOvens, int ovenHeatingTimeFactor,
			int ovenCoolingTimeFactor) {
		this.location = location;
		this.numberDoughKneadingMachines = numberDoughKneadingMachines;
		this.numberOvens = numberOvens;
		this.ovenHeatingTimeFactor = ovenHeatingTimeFactor;
		this.ovenCoolingTimeFactor = ovenCoolingTimeFactor;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public int getNumberDoughKneadingMachines() {
		return numberDoughKneadingMachines;
	}

	public void setNumberDoughKneadingMachines(int numberDoughKneadingMachines) {
		this.numberDoughKneadingMachines = numberDoughKneadingMachines;
	}

	public int getNumberOvens() {
		return numberOvens;
	}

	public void setNumberOvens(int numberOvens) {
		this.numberOvens = numberOvens;
	}

	public int getOvenHeatingTimeFactor() {
		return ovenHeatingTimeFactor;
	}

	public void setOvenHeatingTimeFactor(int ovenHeatingTimeFactor) {
		this.ovenHeatingTimeFactor = ovenHeatingTimeFactor;
	}

	public int getOvenCoolingTimeFactor() {
		return ovenCoolingTimeFactor;
	}

	public void setOvenCoolingTimeFactor(int ovenCoolingTimeFactor) {
		this.ovenCoolingTimeFactor = ovenCoolingTimeFactor;
	}*/

	private List<String> orders = new Vector<>();

	protected void setup() {
		DFAgentDescription dfd = new DFAgentDescription();
		dfd.setName(getAID());
		ServiceDescription sd = new ServiceDescription();
		sd.setType("Factory");
		sd.setName("Bakery");
		dfd.addServices(sd);
		try {
			DFService.register(this, dfd);
		} catch (FIPAException e) {
			// TODO handle
			e.printStackTrace();
		}
		addBehaviour(new Factory.TakeOrder());
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
				System.out.println(orders);
			} else {
				block();
			}
		}
	}
}
