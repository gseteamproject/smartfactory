package procurementBehaviours;

import basicClasses.Order;
import communication.Communication;
import communication.MessageObject;
import interactors.Decision;
import interactors.OrderDataStore;
import jade.lang.acl.ACLMessage;

public class ProcurementDecision extends Decision {
    private String orderText;
    private MessageObject msgObj;

    public ProcurementDecision(ProcurementResponder interactionBehaviour, OrderDataStore dataStore) {
        super(interactionBehaviour, dataStore);
    }

    public ACLMessage execute(ACLMessage request) {
        Order order = Order.gson.fromJson(request.getContent(), Order.class);
        String orderText = order.getTextOfOrder();

        // Agent should send agree or refuse
        // TODO: Add refuse answer (some conditions should be added)
        
        System.out.println("deadline Procurement: " + (order.deadline - System.currentTimeMillis()) * 0.0667);
        
        dataStore.setDeadline(order.deadline - System.currentTimeMillis());        
        System.out.println("currentDeadline: " + order.deadline);
        
        dataStore.setAgent(interactionBehaviour.getAgent().getLocalName());
        System.out.println("currentAgent: " + dataStore.getAgent());

        order.agent = (dataStore.getAgent());
        
        String orderGson = Order.gson.toJson(order);
        request.setContent(orderGson);
        
        dataStore.setRequestMessage(request);
        
        ACLMessage response = request.createReply();
        response.setContent(request.getContent());
        response.setPerformative(ACLMessage.AGREE);

        msgObj = new MessageObject(request, orderText);
        Communication.server.sendMessageToClient(msgObj);
        /*
         * System.out.println(msgObj.getReceivedMessage());
         */

        if (request.getConversationId() == "Materials") {
            msgObj = new MessageObject("AgentProcurement",
                    "I will check materialStorage for materials for " + orderText);
            Communication.server.sendMessageToClient(msgObj);

            /*
             * System.out.
             * println("ProcurementAgent: [request] ProductionAgent asks for materials for "
             * + orderText); System.out.
             * println("ProcurementAgent: [agree] I will check materialStorage for materials for "
             * + orderText);
             */
        } else if (request.getConversationId() == "Take") {
            msgObj = new MessageObject("AgentProcurement",
                    "I will give materials for " + orderText + " from materialStorage");
            Communication.server.sendMessageToClient(msgObj);

            /*
             * System.out.
             * println("ProcurementAgent: [request] ProductionAgent wants to get materials for "
             * + orderText + " from materialStorage"); System.out.println(
             * "ProcurementAgent: [agree] I will give you materials for " + orderText +
             * " from materialStorage");
             */
        }

        return response;
    }

}