package sellerBehaviours;

import java.util.Random;

import communication.Communication;
import communication.MessageObject;
import interactors.OrderDataStore;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;

public class HandleCallForProposal extends CyclicBehaviour {

	private static final long serialVersionUID = 2429876704345890795L;

	private OrderDataStore dataStore;

	public HandleCallForProposal(Agent agent, OrderDataStore dataStore) {
		super(agent);
		this.dataStore = dataStore;
	}

	@Override
	public void action() {
		/*
		 * only messages containing CALL-FOR-PROPOSAL in "performative" slot will be
		 * processed
		 */
		MessageTemplate msgTemplate = MessageTemplate.MatchPerformative(ACLMessage.CFP);
		ACLMessage msg = myAgent.receive(msgTemplate);
		if (msg != null) {
			/* create reply for incoming message with price at "content" slot */
			ACLMessage reply = msg.createReply();
			reply.setPerformative(ACLMessage.PROPOSE);
			int price = new Random().nextInt(100);
			reply.setContent(String.valueOf(price));

			MessageObject msgObj = new MessageObject("AgentProcurementMarket",
					dataStore.getGoodName() + " price is " + price);
			Communication.server.sendMessageToClient(msgObj);
			/*
			 * System.out.println(String.format(name + ": my price is %d", price));
			 */
			/* send reply for incoming message */
			myAgent.send(reply);
		} else {
			/* wait till there is message matching template in message-queue */
			block();
		}
	}

}
