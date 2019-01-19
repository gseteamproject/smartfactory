package productionBehaviours;

import basicClasses.CrossAgentData;
import basicClasses.Order;
import basicClasses.OrderPart;
import basicClasses.Product;
import communication.Communication;
import communication.MessageObject;
import interactors.OrderDataStore;
import interactors.ResponderBehaviour;
import jade.core.behaviours.OneShotBehaviour;

class DeliverToSellingBehaviour extends OneShotBehaviour {

	private static final long serialVersionUID = 313682933400751868L;
	private String orderToGive;
	private String orderText;
	private ResponderBehaviour interactionBehaviour;
	private MessageObject msgObj;
	private OrderDataStore dataStore;

	public DeliverToSellingBehaviour(ResponderBehaviour interactionBehaviour, OrderDataStore dataStore) {
		super(interactionBehaviour.getAgent());
		this.interactionBehaviour = interactionBehaviour;
		this.dataStore = dataStore;
	}

	@Override
	public void action() {
		orderToGive = interactionBehaviour.getRequest().getContent();
		Order order = Order.gson.fromJson(orderToGive, Order.class);
		orderText = order.getTextOfOrder();
		/*
		 * System.out.println("ProductionAgent: Delivering " + orderText +
		 * " to warehouse");
		 */

		msgObj = new MessageObject("AgentProduction", "Delivering " + orderText + " to warehouse");
		Communication.server.sendMessageToClient(msgObj);

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
