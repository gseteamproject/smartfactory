package salesMarketBehaviours;

import java.util.Vector;

import basicAgents.SalesMarket;
import basicClasses.Order;
import communication.Communication;
import communication.MessageObject;
import interactors.AchieveREInitiatorInteractor;
import interactors.OrderDataStore;
import interactors.RequestInteractor;
import jade.core.AID;
import jade.domain.FIPANames;
import jade.lang.acl.ACLMessage;

public class TakeFromWarehouseInitiator extends RequestInteractor implements AchieveREInitiatorInteractor {

    private SalesMarketResponder interactionBehaviour;
    private SalesMarketRequestResult interactor;
    private String orderText;
    public MessageObject msgObj;

    public TakeFromWarehouseInitiator(SalesMarketResponder interactionBehaviour, OrderDataStore dataStore) {
        super(dataStore);
        this.interactionBehaviour = interactionBehaviour;
        this.interactor = SalesMarketResponder.interactor;
    }

    @Override
    public Vector<ACLMessage> prepareRequests(ACLMessage request) {
        request = new ACLMessage(ACLMessage.REQUEST);

        String requestedAction = "Take";
        request.setConversationId(requestedAction);
        request.addReceiver(new AID(("AgentSelling"), AID.ISLOCALNAME));
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

    @Override
    public void handleInform(ACLMessage inform) {
        // TODO Auto-generated method stub
        Order order = Order.gson.fromJson(inform.getContent(), Order.class);
        orderText = order.getTextOfOrder();

        msgObj = new MessageObject(inform, orderText);
        Communication.server.sendMessageToClient(msgObj);
        
        interactionBehaviour.getAgent().addBehaviour(new DeliverToCustomerBehaviour(interactionBehaviour, dataStore));
    }

    @Override
    public void handleFailure(ACLMessage failure) {
        // TODO Auto-generated method stub
        orderText = Order.gson.fromJson(failure.getContent(), Order.class).getTextOfOrder();

        msgObj = new MessageObject(failure, orderText);
        Communication.server.sendMessageToClient(msgObj);
/*
        System.out.println(msgObj.getReceivedMessage());
*/
    }

    @Override
    public int next() {
        // TODO Auto-generated method stub
        return 0;
    }

}
