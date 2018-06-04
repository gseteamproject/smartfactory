package sellingBehaviours;

import java.util.Vector;

import basicAgents.Selling;
import basicClasses.Order;
import communication.Communication;
import communication.MessageObject;
import interactors.AchieveREInitiatorInteractor;
import interactors.OrderDataStore;
import interactors.RequestInteractor;
import jade.core.AID;
import jade.lang.acl.ACLMessage;

public class AskFinancesInitiator extends RequestInteractor implements AchieveREInitiatorInteractor {

    private SellingResponder interactionBehaviour;
    public MessageObject msgObj;

    public AskFinancesInitiator(SellingResponder interactionBehaviour, OrderDataStore dataStore) {
        super(dataStore);
        this.interactionBehaviour = interactionBehaviour;
    }

    @Override
    public Vector<ACLMessage> prepareRequests(ACLMessage request) {
        request = new ACLMessage(ACLMessage.REQUEST);

        String requestedAction = "Order";
        request.addReceiver(new AID(("AgentFinances"), AID.ISLOCALNAME));
        setup(request, requestedAction, true);

        return l;
    }

    @Override
    public void handleInform(ACLMessage inform) {

        handleResponse(inform);

        Order order = Order.gson.fromJson(inform.getContent(), Order.class);
        orderText = order.getTextOfOrder();

        msgObj = new MessageObject("AgentSelling", orderText + " is allowed to produce");
        Communication.server.sendMessageToClient(msgObj);

        ACLMessage msgToProduction = (ACLMessage) inform.clone();
        dataStore.setSubMessage(msgToProduction);

        // add order to queue
        Selling.productionQueue.add(order);

        interactionBehaviour.getAgent().addBehaviour(new AskToProduceBehaviour(interactionBehaviour, dataStore));
    }

    @Override
    public void handleFailure(ACLMessage failure) {

        handleResponse(failure);

        orderText = Order.gson.fromJson(failure.getContent(), Order.class).getTextOfOrder();

        msgObj = new MessageObject("AgentSelling", orderText + " is forbidden to produce");
        Communication.server.sendMessageToClient(msgObj);

    }

    @Override
    public void handleAgree(ACLMessage agree) {

        handleResponse(agree);

        orderText = Order.gson.fromJson(agree.getContent(), Order.class).getTextOfOrder();

        msgObj = new MessageObject("AgentSelling", "Asking finances about " + orderText);
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