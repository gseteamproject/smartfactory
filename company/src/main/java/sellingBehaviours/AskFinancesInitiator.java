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

public class AskFinancesInitiator extends AchieveREInitiatorInteractor {

    private SellingResponder interactionBehaviour;
    public MessageObject msgObj;
    private SellingAgent thisSellingAgent;

    public AskFinancesInitiator(SellingResponder interactionBehaviour, OrderDataStore dataStore) {
        super(dataStore);
        this.interactionBehaviour = interactionBehaviour;
        this.thisSellingAgent = (SellingAgent) dataStore.getThisAgent();
    }

    @Override
    public Vector<ACLMessage> prepareRequests(ACLMessage request) {
        ACLMessage message = new ACLMessage(ACLMessage.REQUEST);

        String requestedAction = "Order";
        message.addReceiver(new AID(("AgentFinances"), AID.ISLOCALNAME));
        setup(message, requestedAction, true);

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
        thisSellingAgent.productionQueue.add(order);

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
