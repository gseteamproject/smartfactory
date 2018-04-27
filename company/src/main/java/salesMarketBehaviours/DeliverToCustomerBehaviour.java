package salesMarketBehaviours;

import basicAgents.SalesMarket;
import basicClasses.Order;
import communication.Communication;
import communication.MessageObject;
import interactors.OrderDataStore;
import jade.core.behaviours.OneShotBehaviour;

class DeliverToCustomerBehaviour extends OneShotBehaviour {

    /**
     * 
     */
    private static final long serialVersionUID = 313682933400751868L;
    private String orderToGive;
    private String orderText;
    private OrderDataStore dataStore;
    private SalesMarketResponder interactionBehaviour;
    private MessageObject msgObj;

    public DeliverToCustomerBehaviour(SalesMarketResponder interactionBehaviour, OrderDataStore dataStore) {
        super(interactionBehaviour.getAgent());
        this.dataStore = dataStore;
        this.interactionBehaviour = interactionBehaviour;
    }

    @Override
    public void action() {
        orderToGive = dataStore.getRequestMessage().getContent();
        Order order = Order.gson.fromJson(orderToGive, Order.class);
        orderText = order.getTextOfOrder();
        /*
         * System.out.println("ProductionAgent: Delivering " + orderText +
         * " to customer");
         */

        msgObj = new MessageObject("AgentProduction", "Delivering " + orderText + " to customer");
        Communication.server.sendMessageToClient(msgObj);

        // if (order.searchInList(SalesMarket.orderQueue) > -1) {
        // interactor.execute(interactionBehaviour.getRequest());
        // }

        if (SalesMarket.orderQueue.remove(order)) {
            msgObj = new MessageObject("AgentSalesMarket", orderText + " is removed from Order queue.");
            Communication.server.sendMessageToClient(msgObj);
            /*
             * System.out.println("SalesMarketAgent: " + orderText +
             * " is removed from Order queue.");
             */
        }
        // Production.isProduced = true;
        dataStore.getRequestResult().execute(interactionBehaviour.getRequest());
    }
}