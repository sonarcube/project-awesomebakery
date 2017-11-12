package org.awesomebakery.agents;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
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
    private List<AID> factories = new Vector<>();
    private static final String customer = "customer";
    private static final String typeFactory = "Factory";
    private static final String seperator = ",";
    private static final String filepath = "./test.csv";

    private List schedulOrder(String seperator, String filepath) throws FileNotFoundException {
        ArrayList<String> scheduleList = new ArrayList<String>();
        Scanner scanner = new Scanner(new File(filepath));
        scanner.useDelimiter(seperator);
        while (scanner.hasNext()) {
            scheduleList.add(scanner.next());
        }
        scanner.close();
        return scheduleList;
    }


    protected void setup() {
        DFAgentDescription dfd = new DFAgentDescription();
        dfd.setName(getAID());
        ServiceDescription sd = new ServiceDescription();
        sd.setType(customer);
        sd.setName(customer);
        dfd.addServices(sd);
        try {
            DFService.register(this, dfd);
        } catch (FIPAException e) {
            // TODO handle
            e.printStackTrace();
        }
        addBehaviour(new FindBakeryBehaviour());
    }

    private class FindBakeryBehaviour extends Behaviour {
        private static final long serialVersionUID = 1L;

        @Override
        public void action() {
            DFAgentDescription template = new DFAgentDescription();
            ServiceDescription sd = new ServiceDescription();
            sd.setType(typeFactory);
            template.addServices(sd);
            try {
                factories.clear();
                DFAgentDescription[] result = DFService.search(myAgent, template);
                for (DFAgentDescription d : result) {
                    factories.add(d.getName());
                }
            } catch (FIPAException fe) {
                fe.printStackTrace();
            }
            if (!factories.isEmpty()) {
                myAgent.addBehaviour(new PlaceOrderBehaviour());
            }

        }

        @Override
        public boolean done() {
            // TODO Auto-generated method stub
            return !factories.isEmpty();
        }

    }

    private MessageTemplate mt = null;

    private class PlaceOrderBehaviour extends OneShotBehaviour {
        private static final long serialVersionUID = 1L;

        @Override
        public void action() {
            ACLMessage cfp = new ACLMessage(ACLMessage.CFP);
            cfp.addReceiver(factories.get(0));
            try {
                cfp.setContent(String.valueOf(schedulOrder(seperator, filepath)));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
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
                    myAgent.addBehaviour(new FindBakeryBehaviour());
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
}
