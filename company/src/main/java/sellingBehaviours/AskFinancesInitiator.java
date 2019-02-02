package sellingBehaviours;

import java.util.Vector;

import basicClasses.Order;
import common.AgentDataStore;
import communication.MessageObject;
import interactors.AchieveREInitiatorInteractor;
import interactors.ResponderBehaviour;
import jade.core.AID;
import jade.lang.acl.ACLMessage;

public class AskFinancesInitiator extends AchieveREInitiatorInteractor {

	public MessageObject msgObj;

	public AskFinancesInitiator(ResponderBehaviour interactionBehaviour, AgentDataStore dataStore) {
		super(interactionBehaviour, dataStore);
	}

	@Override
	public Vector<ACLMessage> prepareRequests(ACLMessage request) {
		ACLMessage message = new ACLMessage(ACLMessage.REQUEST);

		String requestedAction = "Order";
		message.addReceiver(new AID(("AgentFinances"), AID.ISLOCALNAME));
		setup(message, requestedAction, true);

		return l;
	}

	@Override
	public void handleInform(ACLMessage inform) {

		handleResponse(inform);

		Order order = Order.fromJson(inform.getContent());
		orderText = order.getTextOfOrder();

		msgObj = new MessageObject("AgentSelling", orderText + " is allowed to produce");
		dataStore.getAgentPlatform().sendMessageToWebClient(msgObj);

		ACLMessage msgToProduction = (ACLMessage) inform.clone();
		dataStore.setSubMessage(msgToProduction);

		// add order to queue
		dataStore.getProductionQueue().add(order);

		interactionBehaviour.getAgent().addBehaviour(new AskToProduceBehaviour(interactionBehaviour, dataStore));
	}

	@Override
	public void handleFailure(ACLMessage failure) {

		handleResponse(failure);

		Order order = Order.fromJson(failure.getContent());
		orderText = order.getTextOfOrder();

		msgObj = new MessageObject("AgentSelling", orderText + " is forbidden to produce");
		dataStore.getAgentPlatform().sendMessageToWebClient(msgObj);
	}

	@Override
	public void handleAgree(ACLMessage agree) {

		handleResponse(agree);

		Order order = Order.fromJson(agree.getContent());
		orderText = order.getTextOfOrder();

		msgObj = new MessageObject("AgentSelling", "Asking finances about " + orderText);
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
