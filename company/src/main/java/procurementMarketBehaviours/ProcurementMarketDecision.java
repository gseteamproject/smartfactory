package procurementMarketBehaviours;

import basicClasses.Order;
import communication.Communication;
import communication.MessageObject;
import interactors.Decision;
import interactors.OrderDataStore;
import jade.core.AID;
import jade.lang.acl.ACLMessage;

public class ProcurementMarketDecision extends Decision {
    private String orderText;
    private MessageObject msgObj;

    public ProcurementMarketDecision(ProcurementMarketResponder interactionBehaviour, OrderDataStore dataStore) {
        super(interactionBehaviour, dataStore);
        // TODO Auto-generated constructor stub
    }
    
    // CPD-OFF
    // TODO : fix cpd

    public ACLMessage execute(ACLMessage request) {
        Order order = Order.gson.fromJson(request.getContent(), Order.class);
        String orderText = order.getTextOfOrder();

        // Agent should send agree or refuse
        // TODO: Add refuse answer (some conditions should be added)
        
        System.out.println("deadline ProcurementMarket: " + (order.deadline - System.currentTimeMillis()) * 0.0667);
        
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
        response.setSender(new AID(("AgentProcurementMarket"), AID.ISLOCALNAME));

        msgObj = new MessageObject(request, orderText);
        Communication.server.sendMessageToClient(msgObj);
/*
        System.out.println(msgObj.getReceivedMessage());
*/

        return response;
    }

    // CPD-ON
}
