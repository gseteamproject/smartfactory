package sellingBehaviours;

import basicClasses.CrossAgentData;
import basicClasses.Order;
import basicClasses.OrderPart;
import basicClasses.Product;
import communication.Communication;
import communication.MessageObject;
import interactors.OrderDataStore;
import interactors.ResponderBehaviour;
import jade.core.behaviours.OneShotBehaviour;

public class GiveProductToMarketBehaviour extends OneShotBehaviour {

	private static final long serialVersionUID = -6498277261596869382L;
	private ResponderBehaviour interactionBehaviour;
	private String orderToGive;
	private MessageObject msgObj;
	private OrderDataStore dataStore;

	public GiveProductToMarketBehaviour(ResponderBehaviour interactionBehaviour, OrderDataStore dataStore) {
		super(interactionBehaviour.getAgent());
		this.interactionBehaviour = interactionBehaviour;
		orderToGive = dataStore.getRequestMessage().getContent();
		this.dataStore = dataStore;
	}

	@Override
	public void action() {
		Order order = Order.gson.fromJson(orderToGive, Order.class);

		dataStore.setIsTaken(false);
		int takeCount = 0;
		// TODO
		for (OrderPart orderPart : order.orderList) {

			msgObj = new MessageObject("AgentSelling", "Taking " + orderPart.getTextOfOrderPart() + " from warehouse");
			Communication.server.sendMessageToClient(msgObj);

			for (int i = 0; i < orderPart.getAmount(); i++) {
				Product productToGive = orderPart.getProduct();
				/*
				 * System.out.println("SellingAgent: "Taking
				 * " + orderPart.getTextOfOrderPart() + " from warehouse");
				 * Communication.server.sendMessageToClient("SellingAgent", "Taking " +
				 * orderPart.getTextOfOrderPart() + " from warehouse");
				 */
				CrossAgentData.warehouse.remove(productToGive);
			}
			takeCount += 1;
		}
		if (takeCount == order.orderList.size()) {
			dataStore.setIsTaken(true);
			interactionBehaviour.getRequestResult().execute(interactionBehaviour.getRequest());
			// if (Selling.productionQueue.remove(order)) {
			// MessageObject msgObj = new
			// MessageObject(interactionBehaviour.getAgent().getLocalName(),
			// order.getTextOfOrder() + " is removed from Production queue.");
			// Communication.server.sendMessageToClient(msgObj);
			// }
		}
	}
}
