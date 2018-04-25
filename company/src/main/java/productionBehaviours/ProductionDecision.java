package productionBehaviours;

import basicClasses.Order;
import communication.Communication;
import communication.MessageObject;
import interactors.Decision;
import interactors.OrderDataStore;
import jade.core.AID;
import jade.lang.acl.ACLMessage;



public class ProductionDecision extends Decision {

    private MessageObject msgObj;

    public ProductionDecision(ProductionResponder interactionBehaviour, OrderDataStore dataStore) {
        super(interactionBehaviour, dataStore);


    }

    // CPD-OFF
    // TODO : fix cpd

    @Override
    public ACLMessage execute(ACLMessage request) {
        Order order = Order.gson.fromJson(request.getContent(), Order.class);
        String orderText = order.getTextOfOrder();

        msgObj = new MessageObject(request, orderText);
        Communication.server.sendMessageToClient(msgObj);

     /*   System.out.println("ProductionAgent: [request] SellingAgent asks to produce " + orderText);*/
        // Agent should send agree or refuse
        // TODO: Add refuse answer (some conditions should be added)

        System.out.println("deadline: " + (order.deadline - System.currentTimeMillis()) * 0.0667);
        
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
        response.setSender(new AID(("AgentProduction"), AID.ISLOCALNAME));

        msgObj = new MessageObject("AgentProduction", orderText + " will be produced");
        Communication.server.sendMessageToClient(msgObj);
/*
        System.out.println("ProductionAgent: [agree] I will produce " + orderText);
*/

        return response;
    }

    // CPD-ON

}
