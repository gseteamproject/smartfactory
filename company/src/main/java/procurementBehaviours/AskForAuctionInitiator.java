package procurementBehaviours;

import java.util.Vector;

import basicClasses.Order;
import common.AgentDataStore;
import communication.MessageObject;
import interactors.AchieveREInitiatorInteractor;
import interactors.ResponderBehaviour;
import jade.core.AID;
import jade.lang.acl.ACLMessage;

public class AskForAuctionInitiator extends AchieveREInitiatorInteractor {

	private ResponderBehaviour interactionBehaviour;

	private MessageObject msgObj;

	public AskForAuctionInitiator(ResponderBehaviour interactionBehaviour, AgentDataStore dataStore) {
		super(dataStore);
		this.interactionBehaviour = interactionBehaviour;
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
		dataStore.getAgentPlatform().sendMessageToWebClient(msgObj);

		dataStore.setIsInMaterialStorage(true);
		interactionBehaviour.getRequestResult().execute(interactionBehaviour.getRequest());
	}

	@Override
	public void handleFailure(ACLMessage failure) {
		orderText = Order.gson.fromJson(failure.getContent(), Order.class).getTextOfOrder();
		msgObj = new MessageObject(failure, "received [failure] order " + orderText + " was not purchased");
		dataStore.getAgentPlatform().sendMessageToWebClient(msgObj);
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
