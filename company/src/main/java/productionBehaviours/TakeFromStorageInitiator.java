package productionBehaviours;

import java.util.Vector;

import basicClasses.Order;
import common.AgentDataStore;
import communication.MessageObject;
import interactors.AchieveREInitiatorInteractor;
import interactors.ResponderBehaviour;
import jade.core.AID;
import jade.lang.acl.ACLMessage;

public class TakeFromStorageInitiator extends AchieveREInitiatorInteractor {

	private MessageObject msgObj;

	public TakeFromStorageInitiator(ResponderBehaviour interactionBehaviour, AgentDataStore dataStore) {
		super(interactionBehaviour, dataStore);
	}

	@Override
	public Vector<ACLMessage> prepareRequests(ACLMessage request) {
		ACLMessage message = new ACLMessage(ACLMessage.REQUEST);

		String requestedAction = "Take";
		message.addReceiver(new AID(("AgentProcurement"), AID.ISLOCALNAME));
		setup(message, requestedAction, false);

		return l;
	}

	@Override
	public void handleInform(ACLMessage inform) {
		// TODO if deadline was called earlier than inform received message appears to
		// be null. Try to fix this
		orderText = Order.fromJson(inform.getContent()).getTextOfOrder();

		msgObj = new MessageObject(inform, orderText);
		dataStore.getAgentPlatform().sendMessageToWebClient(msgObj);

		msgObj = new MessageObject("AgentProduction", "now have materials for " + orderText);
		dataStore.getAgentPlatform().sendMessageToWebClient(msgObj);
		interactionBehaviour.getAgent().addBehaviour(new DeliverToSellingBehaviour(interactionBehaviour, dataStore));
	}

	@Override
	public void handleFailure(ACLMessage failure) {
		orderText = Order.fromJson(failure.getContent()).getTextOfOrder();

		msgObj = new MessageObject(failure, orderText);
		dataStore.getAgentPlatform().sendMessageToWebClient(msgObj);

		msgObj = new MessageObject("AgentProduction", "materials for " + orderText + " will not be taken from storage");
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
