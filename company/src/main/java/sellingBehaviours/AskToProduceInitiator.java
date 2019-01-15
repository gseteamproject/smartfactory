package sellingBehaviours;

import java.util.Vector;

import basicAgents.SellingAgent;
import basicClasses.Order;
import communication.Communication;
import communication.MessageObject;
import interactors.AchieveREInitiatorInteractor;
import interactors.OrderDataStore;
import jade.core.AID;
import jade.lang.acl.ACLMessage;

public class AskToProduceInitiator extends AchieveREInitiatorInteractor {
    public MessageObject msgObj;

    public AskToProduceInitiator(OrderDataStore dataStore) {
        super(dataStore);
    }

    @Override
    public Vector<ACLMessage> prepareRequests(ACLMessage request) {
        ACLMessage message = new ACLMessage(ACLMessage.REQUEST);

        String requestedAction = "Produce";
        message.addReceiver(new AID(("AgentProduction"), AID.ISLOCALNAME));
        setup(message, requestedAction, true);

        return l;
    }

    @Override
    public void handleInform(ACLMessage inform) {

        handleResponse(inform);

        Order order = Order.gson.fromJson(inform.getContent(), Order.class);
        orderText = order.getTextOfOrder();

        msgObj = new MessageObject("AgentSelling", orderText + " is delivered to warehouse");
        Communication.server.sendMessageToClient(msgObj);
        SellingAgent.isInWarehouse = true;
        // for (Order orderInQueue : SalesMarket.orderQueue) {
        // if (orderInQueue.id == order.id) {
        // order = orderInQueue;
        // }
        // }
        dataStore.getRequestResult().execute(dataStore.getRequestMessage());
        SellingAgent.productionQueue.remove(order);
    }

    @Override
    public void handleFailure(ACLMessage failure) {

        handleResponse(failure);

        orderText = Order.gson.fromJson(failure.getContent(), Order.class).getTextOfOrder();

        msgObj = new MessageObject("AgentSelling", orderText + " is not produced.");
        Communication.server.sendMessageToClient(msgObj);

    }

    @Override
    public void handleAgree(ACLMessage agree) {

        orderText = Order.gson.fromJson(agree.getContent(), Order.class).getTextOfOrder();

        msgObj = new MessageObject(agree, orderText);
        Communication.server.sendMessageToClient(msgObj);

        msgObj = new MessageObject("AgentSelling", "Production of " + orderText + " is initiated.");
        Communication.server.sendMessageToClient(msgObj);

    }

    @Override
    public void handleRefuse(ACLMessage refuse) {

    }

    @Override
    public int next() {

        return 0;
    }

}
