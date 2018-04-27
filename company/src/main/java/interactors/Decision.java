package interactors;

import basicAgents.SalesMarket;
import basicClasses.Order;
import communication.Communication;
import communication.MessageObject;
import communication.Server;
import jade.lang.acl.ACLMessage;

public class Decision {
    protected OrderDataStore dataStore;
    protected ResponderBehaviour interactionBehaviour;
    protected String orderText;
    protected MessageObject msgObj;
    protected ACLMessage response;

    public Decision(ResponderBehaviour interactionBehaviour, OrderDataStore dataStore) {
        this.dataStore = dataStore;
        this.interactionBehaviour = interactionBehaviour;
    }

    public ACLMessage execute(ACLMessage request) {
        return null;
    }

    public void setup(ACLMessage request) {
        Order order = Order.gson.fromJson(request.getContent(), Order.class);
        orderText = order.getTextOfOrder();

        // Agent should send agree or refuse
        // TODO: Add refuse answer (some conditions should be added)

        dataStore.setAgent(interactionBehaviour.getAgent().getLocalName());
        System.out.println("currentAgent: " + dataStore.getAgent());

        if (dataStore.getAgent().equals("AgentSalesMarket")) {
            dataStore.setDeadline(order.deadline);
            SalesMarket.currentDeadline = System.currentTimeMillis() + order.deadline;
            order.deadline = (SalesMarket.currentDeadline);
        } else {
            dataStore.setDeadline(order.deadline - System.currentTimeMillis());
        }

        System.out.println("currentDeadline: " + order.deadline);

        order.agent = dataStore.getAgent();

        String orderGson = Order.gson.toJson(order);
        request.setContent(orderGson);

        dataStore.setRequestMessage(request);

        msgObj = new MessageObject(request, orderText);
        Communication.server.sendMessageToClient(msgObj);
        /*
         * System.out.println(msgObj.getReceivedMessage());
         */
        dataStore.setDeadlineResult(false);

        dataStore.getDeadlineBehaviour().reset(dataStore.getDeadline() * Server.delaytime / 150);

        dataStore.getRequestResult().reset();
        dataStore.getAskBehaviour().setStarted(false);
    }
}
