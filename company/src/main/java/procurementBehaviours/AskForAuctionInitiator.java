package procurementBehaviours;

import java.util.Vector;

import basicAgents.ProcurementAgent;
import basicClasses.Order;
import communication.Communication;
import communication.MessageObject;
import interactors.AchieveREInitiatorInteractor;
import interactors.OrderDataStore;
import interactors.ResponderBehaviour;
import jade.core.AID;
import jade.lang.acl.ACLMessage;

public class AskForAuctionInitiator extends AchieveREInitiatorInteractor {

	private ResponderBehaviour interactionBehaviour;
	public MessageObject msgObj;
	private ProcurementAgent thisProcurementAgent;

	public AskForAuctionInitiator(ResponderBehaviour interactionBehaviour, OrderDataStore dataStore) {
		super(dataStore);
		this.interactionBehaviour = interactionBehaviour;
		thisProcurementAgent = (ProcurementAgent) dataStore.getThisAgent();
	}

	@Override
	public Vector<ACLMessage> prepareRequests(ACLMessage request) {
		ACLMessage message = new ACLMessage(ACLMessage.REQUEST);

		String requestedAction = "Materials";
		message.addReceiver(new AID(("AgentProcurementMarket"), AID.ISLOCALNAME));
		setup(message, requestedAction, true);

		return l;
	}

	@Override
	public void handleInform(ACLMessage inform) {

		orderText = Order.gson.fromJson(inform.getContent(), Order.class).getTextOfOrder();
		msgObj = new MessageObject(inform, "received [inform] order " + orderText + " is delivered to materialStorage");
		Communication.server.sendMessageToClient(msgObj);

		/*
		 * System.out.println("ProcurementAgent: received [inform] " + orderText +
		 * " is delivered to materialStorage");
		 * Communication.server.sendMessageToClient("ProcurementAgent",
		 * "received [inform] " + orderText + " is delivered to materialStorage");
		 */

		thisProcurementAgent.isInMaterialStorage = true;
		interactionBehaviour.getRequestResult().execute(interactionBehaviour.getRequest());
	}

	@Override
	public void handleFailure(ACLMessage failure) {

		orderText = Order.gson.fromJson(failure.getContent(), Order.class).getTextOfOrder();
		msgObj = new MessageObject(failure, "received [failure] order " + orderText + " was not purchased");
		Communication.server.sendMessageToClient(msgObj);

		/*
		 * System.out.println("ProcurementAgent: received [failure] were not purchased"
		 * ); Communication.server.sendMessageToClient("ProcurementAgent",
		 * "received [failure] were not purchased");
		 */

	}

	@Override
	public void handleAgree(ACLMessage agree) {
	}

	@Override
	public void handleRefuse(ACLMessage refuse) {
	}

	@Override
	public int next() {
		return 0;
	}
}
