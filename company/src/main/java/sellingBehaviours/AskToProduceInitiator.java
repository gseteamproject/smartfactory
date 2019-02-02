package sellingBehaviours;

import java.util.Vector;

import basicClasses.Order;
import common.AgentDataStore;
import communication.MessageObject;
import interactors.AchieveREInitiatorInteractor;
import interactors.ResponderBehaviour;
import jade.core.AID;
import jade.lang.acl.ACLMessage;

public class AskToProduceInitiator extends AchieveREInitiatorInteractor {

	private MessageObject msgObj;

	public AskToProduceInitiator(ResponderBehaviour interactionBehaviour, AgentDataStore dataStore) {
		super(interactionBehaviour, dataStore);
	}

	@Override
	public Vector<ACLMessage> prepareRequests(ACLMessage request) {
		ACLMessage message = new ACLMessage(ACLMessage.REQUEST);

		String requestedAction = "Produce";
		message.addReceiver(new AID(("AgentProduction"), AID.ISLOCALNAME));
		setup(message, requestedAction, true);

		return l;
	}

	@Override
	public void handleInform(ACLMessage inform) {
		handleResponse(inform);

		Order order = Order.fromJson(inform.getContent());
		orderText = order.getTextOfOrder();

		msgObj = new MessageObject("AgentSelling", orderText + " is delivered to warehouse");
		dataStore.getAgentPlatform().sendMessageToWebClient(msgObj);
		dataStore.setIsInWarehouse(true);
		interactionBehaviour.getRequestResult().execute(interactionBehaviour.getRequest());
		dataStore.getProductionQueue().remove(order);
	}

	@Override
	public void handleFailure(ACLMessage failure) {
		handleResponse(failure);

		orderText = Order.fromJson(failure.getContent()).getTextOfOrder();

		msgObj = new MessageObject("AgentSelling", orderText + " is not produced.");
		dataStore.getAgentPlatform().sendMessageToWebClient(msgObj);
	}

	@Override
	public void handleAgree(ACLMessage agree) {
		orderText = Order.fromJson(agree.getContent()).getTextOfOrder();

		msgObj = new MessageObject(agree, orderText);
		dataStore.getAgentPlatform().sendMessageToWebClient(msgObj);

		msgObj = new MessageObject("AgentSelling", "Production of " + orderText + " is initiated.");
		dataStore.getAgentPlatform().sendMessageToWebClient(msgObj);
	}

	@Override
	public void handleRefuse(ACLMessage refuse) {
	}

	@Override
	public int next() {
		return 0;
	}
}
