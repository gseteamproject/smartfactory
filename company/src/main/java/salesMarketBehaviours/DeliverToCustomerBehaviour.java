package salesMarketBehaviours;

import basicClasses.CrossAgentData;
import basicClasses.Order;
import common.AgentDataStore;
import communication.MessageObject;
import interactors.ResponderBehaviour;
import jade.core.behaviours.OneShotBehaviour;

class DeliverToCustomerBehaviour extends OneShotBehaviour {

	private static final long serialVersionUID = 313682933400751868L;

	private String orderToGive;

	private String orderText;

	private AgentDataStore dataStore;

	private ResponderBehaviour interactionBehaviour;

	private MessageObject msgObj;

	public DeliverToCustomerBehaviour(ResponderBehaviour interactionBehaviour, AgentDataStore dataStore) {
		super(interactionBehaviour.getAgent());
		this.dataStore = dataStore;
		this.interactionBehaviour = interactionBehaviour;
	}

	@Override
	public void action() {
		orderToGive = dataStore.getRequestMessage().getContent();
		Order order = Order.gson.fromJson(orderToGive, Order.class);
		orderText = order.getTextOfOrder();

		msgObj = new MessageObject("AgentProduction", "Delivering " + orderText + " to customer");
		dataStore.getAgentPlatform().sendMessageToWebClient(msgObj);

		CrossAgentData.orderQueue.remove(order);

		msgObj = new MessageObject("AgentSalesMarket", orderText + " is removed from Order queue.");
		dataStore.getAgentPlatform().sendMessageToWebClient(msgObj);

		interactionBehaviour.getRequestResult().execute(interactionBehaviour.getRequest());
	}
}
