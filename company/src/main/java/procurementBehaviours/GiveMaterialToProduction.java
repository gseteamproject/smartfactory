package procurementBehaviours;

import basicAgents.ProcurementAgent;
import basicClasses.Order;
import basicClasses.OrderPart;
import basicClasses.Product;
import communication.Communication;
import communication.MessageObject;
import interactors.OrderDataStore;
import jade.core.behaviours.OneShotBehaviour;

public class GiveMaterialToProduction extends OneShotBehaviour {

    /**
     * 
     */
    private static final long serialVersionUID = -1386982676634257780L;
    private ProcurementResponder interactionBehaviour;
    private OrderDataStore dataStore;
    private String materialsToGive;
    private String orderText;
    private MessageObject msgObj;

    public GiveMaterialToProduction(ProcurementResponder interactionBehaviour, OrderDataStore dataStore) {
        super(interactionBehaviour.getAgent());
        this.interactionBehaviour = interactionBehaviour;
        this.dataStore = dataStore;
        materialsToGive = dataStore.getRequestMessage().getContent();
    }

    @Override
    public void action() {
        Order order = Order.gson.fromJson(materialsToGive, Order.class);
        orderText = order.getTextOfOrder();
        msgObj = new MessageObject("AgentProcurement", "Taking " + orderText + " from materialStorage.");
        Communication.server.sendMessageToClient(msgObj);

        /*
         * System.out.println("ProcurementAgent: Taking " + orderText +
         * " from materialStorage");
         */

        ProcurementAgent.isGiven = false;

        for (OrderPart orderPart : order.orderList) {
            for (int i = 0; i < orderPart.getAmount(); i++) {
                ProcurementAgent.materialStorage.remove((Product) orderPart.getGood());
            }
        }

        ProcurementAgent.isGiven = true;
        dataStore.getRequestResult().execute(interactionBehaviour.getRequest());
        if (ProcurementAgent.procurementQueue.remove(order)) {
            MessageObject msgObj = new MessageObject(interactionBehaviour.getAgent().getLocalName(),
                    order.getTextOfOrder() + " is removed from Procurement queue.");
            Communication.server.sendMessageToClient(msgObj);
        }
    }
}