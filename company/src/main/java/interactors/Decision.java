package interactors;

import basicClasses.Order;
import communication.Communication;
import communication.MessageObject;
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
        String orderText = order.getTextOfOrder();

        // Agent should send agree or refuse
        // TODO: Add refuse answer (some conditions should be added)

        System.out.println(
                "deadline " + dataStore.getAgent() + ": " + (order.deadline - System.currentTimeMillis()) * 0.0667);

        dataStore.setDeadline(order.deadline - System.currentTimeMillis());
        System.out.println("currentDeadline: " + order.deadline);

        dataStore.setAgent(interactionBehaviour.getAgent().getLocalName());
        System.out.println("currentAgent: " + dataStore.getAgent());

        order.agent = dataStore.getAgent();

        String orderGson = Order.gson.toJson(order);
        request.setContent(orderGson);

        dataStore.setRequestMessage(request);

        msgObj = new MessageObject(request, orderText);
        Communication.server.sendMessageToClient(msgObj);
        /*
         * System.out.println(msgObj.getReceivedMessage());
         */
    }
}
