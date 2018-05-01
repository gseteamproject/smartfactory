package sellingBehaviours;

import basicAgents.Selling;
import basicClasses.Order;
import basicClasses.OrderPart;
import basicClasses.Product;
import communication.Communication;
import communication.MessageObject;
import interactors.OrderDataStore;
import jade.core.behaviours.OneShotBehaviour;
import jade.lang.acl.ACLMessage;

public class CheckWarehouseBehaviour extends OneShotBehaviour {

    /**
     * 
     */
    private static final long serialVersionUID = 3856126876248315456L;
    private ACLMessage requestMessage;
    private OrderDataStore dataStore;
    private SellingResponder interactionBehaviour;
    private MessageObject msgObj;

    public CheckWarehouseBehaviour(SellingResponder interactionBehaviour, OrderDataStore dataStore) {
        super(interactionBehaviour.getAgent());
        requestMessage = dataStore.getRequestMessage();

        this.interactionBehaviour = interactionBehaviour;
        this.dataStore = dataStore;
    }

    @Override
    public void action() {
        // save this request message to reply on it later
        Order order = Order.gson.fromJson(requestMessage.getContent(), Order.class);

        Selling.isInWarehouse = true;
        boolean isInQueue = false;

        // check if this order is not in queue yet
        isInQueue = Selling.productionQueue.contains(order);

        // part of order, that needs to be produced
        Order orderToProduce = new Order();
        orderToProduce.id = order.id;
        orderToProduce.deadline = order.deadline;
        orderToProduce.price = order.price;
        orderToProduce.agent = order.agent;
        
        for (OrderPart orderPart : order.orderList) {
            Product productToCheck = orderPart.getProduct();
            int amount = orderPart.getAmount();
            /*
             * msgObj = new MessageObject("AgentSelling", "Asking warehouse about " +
             * orderPart.getTextOfOrderPart());
             * 
             * System.out.println("SellingAgent: Asking warehouse about " +
             * orderPart.getTextOfOrderPart());
             * Communication.server.sendMessageToClient("SellingAgent",
             * "Asking warehouse about " + orderPart.getTextOfOrderPart());
             */

            int amountInWH = Selling.warehouse.getAmountOfProduct(productToCheck);

            if (amountInWH >= amount) {
                if (Selling.isInWarehouse) {
                    Selling.isInWarehouse = true;
                }

                /*
                 * msgObj = new MessageObject("AgentSelling", "I say that " +
                 * orderPart.getTextOfOrderPart() + " is in warehouse");
                 * 
                 * System.out.println("SellingAgent: I say that " +
                 * orderPart.getTextOfOrderPart() + " is in warehouse");
                 * Communication.server.sendMessageToClient("SellingAgent", "I say that " +
                 * orderPart.getTextOfOrderPart() + " is in warehouse");
                 */
            } else {
                Selling.isInWarehouse = false;

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
            String testGson = Order.gson.toJson(orderToProduce);
            ACLMessage msgToFinances = (ACLMessage) requestMessage.clone();
            msgToFinances.setContent(testGson);
            dataStore.setSubMessage(msgToFinances);

            // // add order to queue
            // Selling.productionQueue.add(order);

            msgObj = new MessageObject("AgentSelling",
                    "Sending an info to Finance Agent to produce " + orderToProduce.getTextOfOrder());
            Communication.server.sendMessageToClient(msgObj);

            /*
             * System.out.println(
             * "SellingAgent: Sending an info to Finance Agent to produce " +
             * orderToProduce.getTextOfOrder());
             * Communication.server.sendMessageToClient("SellingAgent",
             * "Sending an info to Finance Agent to produce " +
             * orderToProduce.getTextOfOrder());
             */

            myAgent.addBehaviour(new AskFinancesBehaviour(interactionBehaviour, dataStore));
        }
    }
}