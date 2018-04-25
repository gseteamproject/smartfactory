package financesBehaviours;

import basicClasses.Order;
import communication.Communication;
import communication.MessageObject;
import interactors.Decision;
import interactors.OrderDataStore;
import jade.lang.acl.ACLMessage;

public class FinancesDecision extends Decision {
    private String orderText;
    private MessageObject msgObj;

    public FinancesDecision(FinancesResponder interactionBehaviour, OrderDataStore dataStore) {
        super(interactionBehaviour, dataStore);
    }

    // CPD-OFF
    // TODO : fix cpd

    public ACLMessage execute(ACLMessage request) {
        Order order = Order.gson.fromJson(request.getContent(), Order.class);
        String orderText = order.getTextOfOrder();

        // Agent should send agree or refuse
        // TODO: Add refuse answer (some conditions should be added)
        
        System.out.println("deadline Finances: " + (order.deadline - System.currentTimeMillis()) * 0.0667);
        
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

        if (request.getConversationId() == "Order") {
            msgObj = new MessageObject("AgentFinances", "has accepted selling of " + orderText);
            Communication.server.sendMessageToClient(msgObj);
        } else if (request.getConversationId() == "Materials") {
            msgObj = new MessageObject("AgentFinances", "has accepted buying of " + orderText);
            Communication.server.sendMessageToClient(msgObj);

        }

        return response;
    }

    // CPD-ON

}