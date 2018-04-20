package interactors;

import java.util.Vector;

import basicClasses.Order;
import communication.Communication;
import communication.MessageObject;
import jade.domain.FIPANames;
import jade.lang.acl.ACLMessage;

public class RequestInteractor {

    protected OrderDataStore dataStore;
    protected String orderText;
    protected MessageObject msgObj;
    protected Vector<ACLMessage> l;

    public RequestInteractor(OrderDataStore dataStore) {
        this.dataStore = dataStore;
    }
    
    protected void setup(ACLMessage request, String requestedAction) {
        request.setConversationId(requestedAction);
        request.setProtocol(FIPANames.InteractionProtocol.FIPA_REQUEST);
        request.setContent(dataStore.getRequestMessage().getContent());

        l = new Vector<ACLMessage>(1);
        l.addElement(request);
    }

    protected void handleResponse(ACLMessage message) {

        Order order = Order.gson.fromJson(message.getContent(), Order.class);
        orderText = order.getTextOfOrder();

        msgObj = new MessageObject(message, orderText);
        Communication.server.sendMessageToClient(msgObj);
    }
}
