package productionBehaviours;

import basicClasses.CrossAgentData;
import basicClasses.Order;
import basicClasses.OrderPart;
import basicClasses.Product;
import common.AgentDataStore;
import communication.MessageObject;
import interactors.ResponderBehaviour;
import jade.core.behaviours.OneShotBehaviour;

class DeliverToSellingBehaviour extends OneShotBehaviour {

	private static final long serialVersionUID = 313682933400751868L;

	private String orderToGive;

	private String orderText;

	private ResponderBehaviour interactionBehaviour;

	private MessageObject msgObj;

	private AgentDataStore dataStore;

	public DeliverToSellingBehaviour(ResponderBehaviour interactionBehaviour, AgentDataStore dataStore) {
		super(interactionBehaviour.getAgent());
		this.interactionBehaviour = interactionBehaviour;
		this.dataStore = dataStore;
	}

	@Override
	public void action() {
		orderToGive = interactionBehaviour.getRequest().getContent();
		Order order = Order.fromJson(orderToGive);
		orderText = order.getTextOfOrder();

		msgObj = new MessageObject("AgentProduction", "Delivering " + orderText + " to warehouse");
		dataStore.getAgentPlatform().sendMessageToWebClient(msgObj);

		for (OrderPart orderPart : order.orderList) {
			Product productToGive = orderPart.getProduct();
			for (int i = 0; i < orderPart.getAmount(); i++) {
				CrossAgentData.warehouse.add(productToGive);
			}
		}
		dataStore.setIsProduced(true);
		interactionBehaviour.getRequestResult().execute(interactionBehaviour.getRequest());
	}
}
