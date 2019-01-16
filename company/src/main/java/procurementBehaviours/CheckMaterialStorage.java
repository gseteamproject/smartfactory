package procurementBehaviours;

import java.util.ArrayList;
import java.util.List;

import basicAgents.ProcurementAgent;
import basicClasses.Good;
import basicClasses.Order;
import basicClasses.OrderPart;
import basicClasses.Product;
import communication.Communication;
import communication.MessageObject;
import interactors.OrderDataStore;
import jade.core.behaviours.OneShotBehaviour;
import jade.lang.acl.ACLMessage;

public class CheckMaterialStorage extends OneShotBehaviour {

    private static final long serialVersionUID = -4869963544017982955L;
    private String requestedMaterial;
    private OrderDataStore dataStore;
    private ProcurementResponder interactionBehaviour;
    private MessageObject msgObj;
    private ACLMessage request;
    private ProcurementAgent thisProcurementAgent;

    public CheckMaterialStorage(ProcurementResponder interactionBehaviour, OrderDataStore dataStore) {
        super(interactionBehaviour.getAgent());
        request = interactionBehaviour.getRequest();
        requestedMaterial = request.getContent();
        this.interactionBehaviour = interactionBehaviour;
        this.dataStore = dataStore;
        thisProcurementAgent = (ProcurementAgent) dataStore.getThisAgent();
    }

    @Override
    public void action() {
        Order order = Order.gson.fromJson(requestedMaterial, Order.class);

        thisProcurementAgent.isInMaterialStorage = true;
        boolean isInQueue = false;

        // check if this order is not in queue yet
        isInQueue = ProcurementAgent.procurementQueue.contains(order);

        // part of order, that needs to be produced
        Order orderToBuy = new Order();
        orderToBuy.id = order.id;
        orderToBuy.deadline = order.deadline;
        orderToBuy.price = order.price;
        orderToBuy.agent = order.agent;

        List<Good> listToRemove = new ArrayList<Good>();

        for (OrderPart orderPart : order.orderList) {
            Product productToCheck = orderPart.getProduct();

            String color = productToCheck.getColor();
            Double size = productToCheck.getSize();

            int amount = orderPart.getAmount();

            List<Good> listToRemovePart = new ArrayList<Good>();

            msgObj = new MessageObject("AgentProcurement", "Asking about " + orderPart.getTextOfOrderPart());
            Communication.server.sendMessageToClient(msgObj);
            /*
             * System.out.println("ProcurementAgent: Asking materialStorage about " +
             * orderPart.getTextOfOrderPart());
             */

            int paintAmountInMS = ProcurementAgent.materialStorage.getAmountOfPaint(color);
            int stoneAmountInMS = ProcurementAgent.materialStorage.getAmountOfStones(size);

            if (paintAmountInMS >= amount && stoneAmountInMS >= amount) {
                msgObj = new MessageObject("AgentProcurement",
                        "I say that materials for " + orderPart.getTextOfOrderPart() + " are in materialStorage. ");
                Communication.server.sendMessageToClient(msgObj);

                /*
                 * System.out.println("ProcurementAgent: I say that materials for " +
                 * orderPart.getTextOfOrderPart() + " are in materialStorage");
                 */
            } else {
                // need to describe multiple statements to check every material
                thisProcurementAgent.isInMaterialStorage = false;

                prepareOrder(orderToBuy, orderPart.getProduct().getPaint(), amount, listToRemovePart, paintAmountInMS);

                prepareOrder(orderToBuy, orderPart.getProduct().getStone(), amount, listToRemovePart, stoneAmountInMS);

                ProcurementAgent.materialStorage.removeAll(listToRemovePart);

                listToRemove.addAll(listToRemovePart);
                /*
                 * System.out.println("paintOrderPart.getAmount() " +
                 * paintOrderPart.getAmount());
                 */
            }
        }

        ProcurementAgent.materialStorage.addAll(listToRemove);

        if (!isInQueue && orderToBuy.orderList.size() > 0) {
            String testGson = Order.gson.toJson(orderToBuy);
            ACLMessage msgToMarket = (ACLMessage) request.clone();
            msgToMarket.setContent(testGson);
            dataStore.setSubMessage(msgToMarket);

            // add order to queue
            ProcurementAgent.procurementQueue.add(order);

            msgObj = new MessageObject("AgentProcurement",
                    "send info to ProcurementMarket to buy materials for " + orderToBuy.getTextOfOrder());
            Communication.server.sendMessageToClient(msgObj);
            /*
             * System.out.
             * println("ProcurementAgent: send info to ProcurementMarket to buy materials for "
             * + orderToBuy.getTextOfOrder());
             */
            // TODO: create another Set method
            myAgent.addBehaviour(new AskForAuction(interactionBehaviour, dataStore));
        }
    }

	private void prepareOrder(Order order, Good good, int amount, List<Good> listToRemovePart, int amountInMS) {
		int limit;
		if (amount > amountInMS) {
			limit = amountInMS;
		} else {
			limit = amount;
		}

		for (int i = 1; i <= limit; i++) {
			listToRemovePart.add(good);
		}

		if (limit >= 0) {
			order.addGood(good, amount - limit);
		}
	}
}
