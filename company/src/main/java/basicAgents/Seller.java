package basicAgents;

import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import communication.Communication;
import communication.MessageObject;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.domain.DFService;
import jade.domain.FIPAException;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;

public class Seller extends Agent {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private static final long serialVersionUID = -7418692714860762106L;
    private MessageObject msgObj;
    private String name;
    
    @Override
    protected void setup() {
        Object[] args = getArguments();
        name = args[0].toString();
        ServiceDescription serviceDescription = new ServiceDescription();
        serviceDescription.setName(name);
        serviceDescription.setType("procurement-service");
        DFAgentDescription agentDescription = new DFAgentDescription();
        agentDescription.setName(getAID());
        agentDescription.addServices(serviceDescription);
		try {
			DFService.register(this, agentDescription);
		} catch (FIPAException exception) {
			logger.error("register failed", exception);
		}

        /* registering Behaviours to react for different types of messages */
        addBehaviour(new HandleAcceptProposal());
        addBehaviour(new HandleCallForProposal());
    }

    @Override
	protected void takeDown() {
		try {
			DFService.deregister(this);
		} catch (FIPAException exception) {
			logger.error("deregister failed", exception);
		}
    }

    class HandleCallForProposal extends CyclicBehaviour {
        private static final long serialVersionUID = 2429876704345890795L;

        @Override
        public void action() {
            /*
             * only messages containing CALL-FOR-PROPOSAL in "performative" slot will be
             * processed
             */
            MessageTemplate msgTemplate = MessageTemplate.MatchPerformative(ACLMessage.CFP);
            ACLMessage msg = receive(msgTemplate);
            if (msg != null) {
                /* create reply for incoming message with price at "content" slot */
                ACLMessage reply = msg.createReply();
                reply.setPerformative(ACLMessage.PROPOSE);
                int price = new Random().nextInt(100);
                reply.setContent(String.valueOf(price));

                msgObj = new MessageObject("AgentProcurementMarket", name + " price is " + price);
                Communication.server.sendMessageToClient(msgObj);
                /*
                 * System.out.println(String.format(name + ": my price is %d", price));
                 */
                /* send reply for incoming message */
                send(reply);
            } else {
                /* wait till there is message matching template in message-queue */
                block();
            }
        }
    }

    class HandleAcceptProposal extends CyclicBehaviour {
        private static final long serialVersionUID = 8759104857697556076L;

        @Override
        public void action() {
            /*
             * only message containg ACCEPT-PROPOSAL in "performative" slot will be
             * processed
             */
            MessageTemplate msgTemplate = MessageTemplate.MatchPerformative(ACLMessage.ACCEPT_PROPOSAL);
            ACLMessage msg = receive(msgTemplate);
            if (msg != null) {
                /* create reply for incoming message */
                ACLMessage reply = msg.createReply();
                reply.setPerformative(ACLMessage.INFORM);
                msgObj = new MessageObject("AgentProcurementMarket", "Delivering " + name + "(s).");
                Communication.server.sendMessageToClient(msgObj);

                /*
                 * System.out.println("delivering...");
                 */
                /* send reply for incoming message */
                send(reply);
            } else {
                /* wait till there is message matching template in message-queue */
                block();
            }

        }
    }
}