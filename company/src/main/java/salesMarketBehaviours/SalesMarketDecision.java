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
        Communication.server.sendMessageToClient(msgObj);

        setup(request);

        // Agent should send agree or refuse
        response = request.createReply();
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
