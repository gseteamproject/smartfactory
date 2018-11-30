package productionBehaviours;

import java.util.Vector;

import basicClasses.Order;
import communication.Communication;
import communication.MessageObject;
import interactors.AchieveREInitiatorInteractor;
import interactors.OrderDataStore;
import jade.core.AID;
import jade.lang.acl.ACLMessage;

public class TakeFromStorageInitiator extends AchieveREInitiatorInteractor {

    private ProductionResponder interactionBehaviour;
    public MessageObject msgObj;

    public TakeFromStorageInitiator(ProductionResponder interactionBehaviour, OrderDataStore dataStore) {
        super(dataStore);
        this.interactionBehaviour = interactionBehaviour;
    }

    @Override
    public Vector<ACLMessage> prepareRequests(ACLMessage request) {
        ACLMessage message = new ACLMessage(ACLMessage.REQUEST);

        String requestedAction = "Take";
        message.addReceiver(new AID(("AgentProcurement"), AID.ISLOCALNAME));
        setup(message, requestedAction, false);

        return l;
    }

    @Override
    public void handleInform(ACLMessage inform) {
        // TODO if deadline was called earlier than inform received message appears to
        // be null. Try to fix this

        orderText = Order.gson.fromJson(inform.getContent(), Order.class).getTextOfOrder();

        msgObj = new MessageObject(inform, orderText);
        Communication.server.sendMessageToClient(msgObj);

        /*
         * System.out.println( "ProductionAgent: received [inform] materials for " +
         * orderText + " will be taken from storage");
         */

        msgObj = new MessageObject("AgentProduction", "now have materials for " + orderText);
        Communication.server.sendMessageToClient(msgObj);

        /*
         * System.out.println("ProductionAgent: Now I have materials for " + orderText);
         */
        interactionBehaviour.getAgent().addBehaviour(new DeliverToSellingBehaviour(interactionBehaviour, dataStore));
    }

    @Override
    public void handleFailure(ACLMessage failure) {

        orderText = Order.gson.fromJson(failure.getContent(), Order.class).getTextOfOrder();

        msgObj = new MessageObject(failure, orderText);
        Communication.server.sendMessageToClient(msgObj);

        msgObj = new MessageObject("AgentProduction", "materials for " + orderText + " will not be taken from storage");
        Communication.server.sendMessageToClient(msgObj);

        /*
         * System.out.println( "ProductionAgent: received [failure] materials for " +
         * orderText + " will not be taken from storage");
         */
    }

    @Override
    public void handleAgree(ACLMessage agree) {

    }

    @Override
    public void handleRefuse(ACLMessage refuse) {

    }

    @Override
    public int next() {

        return 0;
    }

}
