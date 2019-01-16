package procurementBehaviours;

import basicClasses.Order;
import communication.Communication;
import communication.MessageObject;
import interactors.OrderDataStore;
import jade.core.behaviours.OneShotBehaviour;

public class AskForAuction extends OneShotBehaviour {

	private static final long serialVersionUID = -4435715111290249737L;
	private String materialsToRequest;
	private String orderText;
	private OrderDataStore dataStore;
	private ProcurementResponder interactionBehaviour;
	private MessageObject msgObj;

	public AskForAuction(ProcurementResponder interactionBehaviour, OrderDataStore dataStore) {
		super(interactionBehaviour.getAgent());
		this.interactionBehaviour = interactionBehaviour;
		this.dataStore = dataStore;
	}

	@Override
	public void action() {
		materialsToRequest = interactionBehaviour.getRequest().getContent();
		orderText = Order.gson.fromJson(materialsToRequest, Order.class).getTextOfOrder();
		msgObj = new MessageObject("AgentProduction", "is asking to get materials for " + orderText);
		Communication.server.sendMessageToClient(msgObj);
		myAgent.addBehaviour(new AskForAuctionInitiatorBehaviour(interactionBehaviour, dataStore));
	}
}
