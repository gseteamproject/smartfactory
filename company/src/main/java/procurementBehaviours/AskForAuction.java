package procurementBehaviours;

import basicClasses.Order;
import common.AgentDataStore;
import communication.MessageObject;
import interactors.ResponderBehaviour;
import jade.core.behaviours.OneShotBehaviour;

public class AskForAuction extends OneShotBehaviour {

	private static final long serialVersionUID = -4435715111290249737L;

	private String materialsToRequest;

	private String orderText;

	private AgentDataStore dataStore;

	private ResponderBehaviour interactionBehaviour;

	private MessageObject msgObj;

	public AskForAuction(ResponderBehaviour interactionBehaviour, AgentDataStore dataStore) {
		super(interactionBehaviour.getAgent());
		this.interactionBehaviour = interactionBehaviour;
		this.dataStore = dataStore;
	}

	@Override
	public void action() {
		materialsToRequest = interactionBehaviour.getRequest().getContent();
		orderText = Order.fromJson(materialsToRequest).getTextOfOrder();
		msgObj = new MessageObject("AgentProduction", "is asking to get materials for " + orderText);
		dataStore.getAgentPlatform().sendMessageToWebClient(msgObj);
		myAgent.addBehaviour(new AskForAuctionInitiatorBehaviour(interactionBehaviour, dataStore));
	}
}
