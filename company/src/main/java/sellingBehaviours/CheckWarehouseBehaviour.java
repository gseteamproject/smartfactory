package sellingBehaviours;

import basicClasses.CrossAgentData;
import basicClasses.Order;
import basicClasses.OrderPart;
import basicClasses.Product;
import common.AgentDataStore;
import communication.MessageObject;
import interactors.ResponderBehaviour;
import jade.core.behaviours.OneShotBehaviour;
import jade.lang.acl.ACLMessage;

public class CheckWarehouseBehaviour extends OneShotBehaviour {

	private static final long serialVersionUID = 3856126876248315456L;

	private ACLMessage requestMessage;

	private AgentDataStore dataStore;

	private ResponderBehaviour interactionBehaviour;

	private MessageObject msgObj;

	public CheckWarehouseBehaviour(ResponderBehaviour interactionBehaviour, AgentDataStore dataStore) {
		super(interactionBehaviour.getAgent());
		requestMessage = interactionBehaviour.getRequest();

		this.interactionBehaviour = interactionBehaviour;
		this.dataStore = dataStore;
	}

	@Override
	public void action() {
		Order order = dataStore.getOrder();

		dataStore.setIsInWarehouse(true);
		boolean isInQueue = false;

		// check if this order is not in queue yet
		isInQueue = dataStore.getProductionQueue().contains(order);

		// part of order, that needs to be produced
		Order orderToProduce = new Order();
		orderToProduce.id = order.id;
		orderToProduce.deadline = order.deadline;
		orderToProduce.price = order.price;
		orderToProduce.agent = order.agent;

		for (OrderPart orderPart : order.orderList) {
			Product productToCheck = orderPart.getProduct();
			int amount = orderPart.getAmount();
			int amountInWH = CrossAgentData.warehouse.getAmountOfProduct(productToCheck);

			if (amountInWH >= amount) {
				if (dataStore.getIsInWarehouse()) {
					dataStore.setIsInWarehouse(true);
				}
			} else {
				dataStore.setIsInWarehouse(false);

				// creating new instance of OrderPart to change its amount
				OrderPart newOrderPart = new OrderPart(orderPart.getProduct());
				newOrderPart.setAmount(orderPart.getAmount() - amountInWH);
				if (newOrderPart.getAmount() > 0) {
					orderToProduce.orderList.add(newOrderPart);
				}
			}
		}

		// productToCheck needs to be produced
		if (!isInQueue && (orderToProduce.orderList.size() > 0)) {
			String testGson = orderToProduce.toJson();
			ACLMessage msgToFinances = (ACLMessage) requestMessage.clone();
			msgToFinances.setContent(testGson);
			dataStore.setSubMessage(msgToFinances);

			msgObj = new MessageObject("AgentSelling",
					"Sending an info to Finance Agent to produce " + orderToProduce.getTextOfOrder());
			dataStore.getAgentPlatform().sendMessageToWebClient(msgObj);

			myAgent.addBehaviour(new AskFinancesBehaviour(interactionBehaviour, dataStore));
		}
	}
}
