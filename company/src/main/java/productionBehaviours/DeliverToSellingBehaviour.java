package productionBehaviours;

import basicAgents.Production;
import basicAgents.Selling;
import basicClasses.Order;
import basicClasses.OrderPart;
import basicClasses.Product;
import communication.Communication;
import communication.MessageObject;
import interactors.OrderDataStore;
import jade.core.behaviours.OneShotBehaviour;
import jade.core.event.MessageAdapter;

class DeliverToSellingBehaviour extends OneShotBehaviour {

    /**
     * 
     */
    private static final long serialVersionUID = 313682933400751868L;
    private String orderToGive;
    private String orderText;
    private OrderDataStore dataStore;
    private ProductionResponder interactionBehaviour;
    private ProductionRequestResult interactor;
    private MessageObject msgObj;


    public DeliverToSellingBehaviour(ProductionResponder interactionBehaviour, OrderDataStore dataStore) {
        super(interactionBehaviour.getAgent());
        this.dataStore = dataStore;
        this.interactionBehaviour = interactionBehaviour;
        this.interactor = ProductionResponder.interactor;
    }

    @Override
    public void action() {
        orderToGive = interactionBehaviour.getRequest().getContent();
        Order order = Order.gson.fromJson(orderToGive, Order.class);
        orderText = order.getTextOfOrder();
/*
        System.out.println("ProductionAgent: Delivering " + orderText + " to warehouse");
*/

        msgObj = new MessageObject("AgentProduction" , "Delivering " + orderText + " to warehouse");
        Communication.server.sendMessageToClient(msgObj);


        for (OrderPart orderPart : order.orderList) {
            Product productToGive = orderPart.getProduct();
            for (int i = 0; i < orderPart.getAmount(); i++) {
                Selling.warehouse.add(productToGive);
            }
        }
        Production.isProduced = true;
        interactor.execute(interactionBehaviour.getRequest());
    }
}