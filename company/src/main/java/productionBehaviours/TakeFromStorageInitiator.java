package productionBehaviours;

import java.util.Vector;

import basicClasses.Order;
import communication.Communication;
import communication.MessageObject;
import interactors.AchieveREInitiatorInteractor;
import interactors.OrderDataStore;
import interactors.RequestInteractor;
import jade.core.AID;
import jade.domain.FIPANames;
import jade.lang.acl.ACLMessage;

public class TakeFromStorageInitiator extends RequestInteractor implements AchieveREInitiatorInteractor {

    private ProductionResponder interactionBehaviour;
    private String orderText;
    public MessageObject msgObj;

    public TakeFromStorageInitiator(ProductionResponder interactionBehaviour, OrderDataStore dataStore) {
        super(dataStore);
        this.interactionBehaviour = interactionBehaviour;
    }

    // CPD-OFF
    // TODO : fix cpd

    @Override
    public Vector<ACLMessage> prepareRequests(ACLMessage request) {
        request = new ACLMessage(ACLMessage.REQUEST);

        String requestedAction = "Take";
        request.setConversationId(requestedAction);
        request.addReceiver(new AID(("AgentProcurement"), AID.ISLOCALNAME));
        request.setProtocol(FIPANames.InteractionProtocol.FIPA_REQUEST);
        request.setContent(dataStore.getRequestMessage().getContent());

        Vector<ACLMessage> l = new Vector<ACLMessage>(1);
        l.addElement(request);
        return l;
    }

    @Override
    public void handleAgree(ACLMessage agree) {
        // TODO Auto-generated method stub

    }

    @Override
    public void handleRefuse(ACLMessage refuse) {
        // TODO Auto-generated method stub

    }

    // CPD-ON

    @Override
    public void handleInform(ACLMessage inform) {
        // TODO if deadline was called earlier than inform received message appears to be null. Try to fix this
        
        // TODO Auto-generated method stub
        orderText = Order.gson.fromJson(inform.getContent(), Order.class).getTextOfOrder();

        msgObj = new MessageObject(inform, orderText);
        Communication.server.sendMessageToClient(msgObj);

      /*  System.out.println(
                "ProductionAgent: received [inform] materials for " + orderText + " will be taken from storage");*/

        msgObj = new MessageObject("AgentProduction" , "now have materials for " + orderText);
        Communication.server.sendMessageToClient(msgObj);

/*
        System.out.println("ProductionAgent: Now I have materials for " + orderText);
*/
        interactionBehaviour.getAgent().addBehaviour(new DeliverToSellingBehaviour(interactionBehaviour, dataStore));
    }

    @Override
    public void handleFailure(ACLMessage failure) {
        // TODO Auto-generated method stub
        orderText = Order.gson.fromJson(failure.getContent(), Order.class).getTextOfOrder();

        msgObj = new MessageObject(failure, orderText);
        Communication.server.sendMessageToClient(msgObj);

        msgObj = new MessageObject("AgentProduction" , "materials for " +  orderText + " will not be taken from storage");
        Communication.server.sendMessageToClient(msgObj);

        /* System.out.println(
                "ProductionAgent: received [failure] materials for " + orderText + " will not be taken from storage");*/
    }

    @Override
    public int next() {
        // TODO Auto-generated method stub
        return 0;
    }

}
