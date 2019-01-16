package sellingBehaviours;

import basicAgents.SellingAgent;
import basicClasses.Order;
import basicClasses.OrderPart;
import basicClasses.Product;
import communication.Communication;
import communication.MessageObject;
import interactors.OrderDataStore;
import jade.core.behaviours.OneShotBehaviour;

public class GiveProductToMarketBehaviour extends OneShotBehaviour {

	private static final long serialVersionUID = -6498277261596869382L;
	private SellingResponder interactionBehaviour;
	private OrderDataStore dataStore;
	private String orderToGive;
	private MessageObject msgObj;
	private SellingAgent thisSellingAgent;

    public GiveProductToMarketBehaviour(SellingResponder interactionBehaviour, OrderDataStore dataStore) {
        super(interactionBehaviour.getAgent());
        this.interactionBehaviour = interactionBehaviour;
        this.dataStore = dataStore;
        orderToGive = dataStore.getRequestMessage().getContent();
        thisSellingAgent = (SellingAgent) dataStore.getThisAgent();
    }

    @Override
    public void action() {
        Order order = Order.gson.fromJson(orderToGive, Order.class);

        thisSellingAgent.isTaken = false;
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
                SellingAgent.warehouse.remove(productToGive);
            }
            takeCount += 1;
        }
        if (takeCount == order.orderList.size()) {
            thisSellingAgent.isTaken = true;
            dataStore.getRequestResult().execute(interactionBehaviour.getRequest());
            // if (Selling.productionQueue.remove(order)) {
            // MessageObject msgObj = new
            // MessageObject(interactionBehaviour.getAgent().getLocalName(),
            // order.getTextOfOrder() + " is removed from Production queue.");
            // Communication.server.sendMessageToClient(msgObj);
            // }
        }
    }
}