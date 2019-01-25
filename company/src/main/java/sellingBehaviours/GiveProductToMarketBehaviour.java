package sellingBehaviours;

import basicClasses.CrossAgentData;
import basicClasses.Order;
import basicClasses.OrderPart;
import basicClasses.Product;
import common.AgentDataStore;
import communication.MessageObject;
import interactors.ResponderBehaviour;
import jade.core.behaviours.OneShotBehaviour;

public class GiveProductToMarketBehaviour extends OneShotBehaviour {

	private static final long serialVersionUID = -6498277261596869382L;

	private ResponderBehaviour interactionBehaviour;

	private String orderToGive;

	private MessageObject msgObj;

	private AgentDataStore dataStore;

	public GiveProductToMarketBehaviour(ResponderBehaviour interactionBehaviour, AgentDataStore dataStore) {
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
			dataStore.getAgentPlatform().sendMessageToWebClient(msgObj);

			for (int i = 0; i < orderPart.getAmount(); i++) {
				Product productToGive = orderPart.getProduct();
				CrossAgentData.warehouse.remove(productToGive);
			}
			takeCount += 1;
		}
		if (takeCount == order.orderList.size()) {
			dataStore.setIsTaken(true);
			interactionBehaviour.getRequestResult().execute(interactionBehaviour.getRequest());
		}
	}
}
