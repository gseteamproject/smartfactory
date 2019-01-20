package sellerBehaviours;

import communication.Communication;
import communication.MessageObject;
import interactors.OrderDataStore;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;

public class HandleAcceptProposal extends CyclicBehaviour {

	private static final long serialVersionUID = 8759104857697556076L;

	private OrderDataStore dataStore;

	public HandleAcceptProposal(Agent agent, OrderDataStore dataStore) {
		super(agent);
		this.dataStore = dataStore;
	}

	@Override
	public void action() {
		/*
		 * only message containg ACCEPT-PROPOSAL in "performative" slot will be
		 * processed
		 */
		MessageTemplate msgTemplate = MessageTemplate.MatchPerformative(ACLMessage.ACCEPT_PROPOSAL);
		ACLMessage msg = myAgent.receive(msgTemplate);
		if (msg != null) {
			/* create reply for incoming message */
			ACLMessage reply = msg.createReply();
			reply.setPerformative(ACLMessage.INFORM);
			MessageObject msgObj = new MessageObject("AgentProcurementMarket",
					"Delivering " + dataStore.getGoodName() + "(s).");
			Communication.server.sendMessageToClient(msgObj);

			/*
			 * System.out.println("delivering...");
			 */
			/* send reply for incoming message */
			myAgent.send(reply);
		} else {
			/* wait till there is message matching template in message-queue */
			block();
		}

	}
}
