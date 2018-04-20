package salesMarketBehaviours;

import basicAgents.SalesMarket;
import basicClasses.Order;
import communication.Communication;
import communication.MessageObject;
import interactors.Decision;
import interactors.OrderDataStore;
import jade.core.AID;
import jade.lang.acl.ACLMessage;

public class SalesMarketDecision extends Decision {

    public SalesMarketDecision(SalesMarketResponder interactionBehaviour, OrderDataStore dataStore) {
        super(interactionBehaviour, dataStore);
    }

    @Override
    public ACLMessage execute(ACLMessage request) {

        Order order = Order.gson.fromJson(request.getContent(), Order.class);
        String orderText = order.getTextOfOrder();

        MessageObject msgObj = new MessageObject(request, "has ordered " + orderText);
//        MessageObject msgObj = new MessageObject("AgentSalesMarket", orderText +  " is here");
        Communication.server.sendMessageToClient(msgObj);

        dataStore.setDeadline(order.deadline);
        
        System.out.println("deadline SalesMarket: " + order.deadline  * 0.0667);
        SalesMarket.currentDeadline = System.currentTimeMillis() + order.deadline;
        
        order.deadline = (SalesMarket.currentDeadline);
        System.out.println("currentDeadline: " + order.deadline);
        
        dataStore.setAgent(interactionBehaviour.getAgent().getLocalName());
        System.out.println("currentAgent: " + dataStore.getAgent());

        order.agent = (dataStore.getAgent());
        
        String orderGson = Order.gson.toJson(order);
        request.setContent(orderGson);
        
        dataStore.setRequestMessage(request);
        
        // Agent should send agree or refuse
        ACLMessage response = request.createReply();
        response.setContent(request.getContent());
        response.setSender(new AID(("AgentSalesMarket"), AID.ISLOCALNAME));
        if (!SalesMarket.orderQueue.contains(order)) {
            response.setPerformative(ACLMessage.AGREE);
            msgObj = new MessageObject(response, "has accepted order of " + orderText);
            Communication.server.sendMessageToClient(msgObj);

        } else {
            response.setPerformative(ACLMessage.REFUSE);
            msgObj = new MessageObject(response, "has rejected order of " + orderText);
            Communication.server.sendMessageToClient(msgObj);

        }
        return response;
    }
}
