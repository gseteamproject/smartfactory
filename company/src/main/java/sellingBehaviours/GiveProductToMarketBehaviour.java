package sellingBehaviours;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import basicAgents.Selling;
import basicClasses.Order;
import basicClasses.OrderPart;
import basicClasses.Product;
import communication.Communication;
import communication.MessageObject;
import interactors.OrderDataStore;
import jade.core.behaviours.OneShotBehaviour;

public class GiveProductToMarketBehaviour extends OneShotBehaviour {

    /**
     * 
     */
    private static final long serialVersionUID = -6498277261596869382L;
    private SellingResponder interactionBehaviour;
    private SellingRequestResult interactor;
    private String orderToGive;
    private MessageObject msgObj;

    public GiveProductToMarketBehaviour(SellingResponder interactionBehaviour, OrderDataStore dataStore) {
        super(interactionBehaviour.getAgent());
        this.interactionBehaviour = interactionBehaviour;
        this.interactor = SellingResponder.interactor;
        orderToGive = interactionBehaviour.getRequest().getContent();
    }

    @Override
    public void action() {
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        Order order = gson.fromJson(orderToGive, Order.class);

        Selling.isTaken = false;

        int takeCount = 0;
        for (OrderPart orderPart : order.orderList) {
            Product productToGive = orderPart.getProduct();

            msgObj = new MessageObject("AgentSelling", "Taking " + orderPart.getTextOfOrderPart() + " from warehouse");
            Communication.server.sendMessageToClient(msgObj);

            /*
             * System.out.println("SellingAgent: "Taking
             * " + orderPart.getTextOfOrderPart() + " from warehouse");
             * Communication.server.sendMessageToClient("SellingAgent", "Taking " +
             * orderPart.getTextOfOrderPart() + " from warehouse");
             */
            Selling.warehouse.remove(productToGive);
            takeCount += 1;
        }
        if (takeCount == order.orderList.size()) {
            Selling.isTaken = true;
            interactor.execute(interactionBehaviour.getRequest());
        }
    }
}