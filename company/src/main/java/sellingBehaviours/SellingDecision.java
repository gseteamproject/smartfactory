package sellingBehaviours;

import basicClasses.Order;
import communication.Communication;
import communication.MessageObject;
import interactors.Decision;
import interactors.OrderDataStore;
import jade.core.AID;
import jade.lang.acl.ACLMessage;

public class SellingDecision extends Decision {

    public SellingDecision(SellingResponder interactionBehaviour, OrderDataStore dataStore) {
        super(interactionBehaviour, dataStore);
    }

    // CPD-OFF
    // TODO : fix cpd

    @Override
    public ACLMessage execute(ACLMessage request) {
        // Selling reacts on SalesMarket's request
        
        Order order = Order.gson.fromJson(request.getContent(), Order.class);
        String orderText = order.getTextOfOrder();
        MessageObject msgObj = new MessageObject(request, orderText);
        // Agent should send agree or refuse
        // TODO: Add refuse answer (some conditions should be added)
        
        System.out.println("deadline Selling: " + (order.deadline - System.currentTimeMillis()) * 0.0667);
        
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
        response.setSender(new AID(("AgentSelling"), AID.ISLOCALNAME));

        // response.setPerformative(ACLMessage.REFUSE);

        /* System.out.println(msgObj.getReceivedMessage()); */

        if (request.getConversationId() == "Ask") {

            msgObj = new MessageObject(response, orderText);
            Communication.server.sendMessageToClient(msgObj);

            /*System.out.println(msgObj.getReceivedMessage());
            Communication.server.sendMessageToClient("SellingAgent", "[agree] I will check warehouse for " + orderText);*/
        } else if (request.getConversationId() == "Take") {

            msgObj = new MessageObject(response, orderText);
            Communication.server.sendMessageToClient(msgObj);

            /*System.out.println(msgObj.getReceivedMessage());
            Communication.server.sendMessageToClient("SellingAgent",
                    "[agree] I will give you " + orderText + " from warehouse");*/
        }
        
        return response;
    }

    // CPD-ON
}
