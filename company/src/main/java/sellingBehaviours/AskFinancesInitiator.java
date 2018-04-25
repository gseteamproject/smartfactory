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
import jade.domain.FIPANames;
import jade.lang.acl.ACLMessage;

public class AskFinancesInitiator extends RequestInteractor implements AchieveREInitiatorInteractor {

    private SellingResponder interactionBehaviour;
    private SellingRequestResult interactor;
    private String orderText;
    public MessageObject msgObj;

    public AskFinancesInitiator(SellingResponder interactionBehaviour, OrderDataStore dataStore) {
        super(dataStore);
        this.interactionBehaviour = interactionBehaviour;
        this.interactor = SellingResponder.interactor;
    }

    // CPD-OFF
    // TODO : fix cpd

    @Override
    public Vector<ACLMessage> prepareRequests(ACLMessage request) {
        request = new ACLMessage(ACLMessage.REQUEST);

        String requestedAction = "Order";
        request.setConversationId(requestedAction);
        request.addReceiver(new AID(("AgentFinances"), AID.ISLOCALNAME));
        request.setProtocol(FIPANames.InteractionProtocol.FIPA_REQUEST);
        request.setContent(dataStore.getRequestMessage().getContent());

        Vector<ACLMessage> l = new Vector<ACLMessage>(1);
        l.addElement(request);

        return l;
    }

    @Override
    public void handleAgree(ACLMessage agree) {
        // TODO Auto-generated method stub
        orderText = Order.gson.fromJson(agree.getContent(), Order.class).getTextOfOrder();

        msgObj = new MessageObject(agree, orderText);
        Communication.server.sendMessageToClient(msgObj);

        msgObj = new MessageObject("AgentSelling", "Production of " + orderText + " is initiated.");
        Communication.server.sendMessageToClient(msgObj);

    }

    @Override
    public void handleRefuse(ACLMessage refuse) {
        // TODO Auto-generated method stub
    }

    // CPD-ON

    @Override
    public void handleInform(ACLMessage inform) {
        // TODO Auto-generated method stub

        Order order = Order.gson.fromJson(inform.getContent(), Order.class);
        orderText = order.getTextOfOrder();

        msgObj = new MessageObject(inform, orderText);
        Communication.server.sendMessageToClient(msgObj);

        msgObj = new MessageObject("AgentSelling", orderText + " is allowed to produce");
        Communication.server.sendMessageToClient(msgObj);

        ACLMessage msgToProduction = (ACLMessage) inform.clone();
        dataStore.setSubMessage(msgToProduction);

        // add order to queue
        Selling.productionQueue.add(order);

        interactionBehaviour.getAgent()
                .addBehaviour(new AskToProduceBehaviour(interactionBehaviour, dataStore));
    }

    @Override
    public void handleFailure(ACLMessage failure) {
        // TODO Auto-generated method stub
        orderText = Order.gson.fromJson(failure.getContent(), Order.class).getTextOfOrder();
        msgObj = new MessageObject(failure, orderText);
        Communication.server.sendMessageToClient(msgObj);
        /* System.out.println("SellingAgent: received [failure] is not produced"); */

        msgObj = new MessageObject("AgentSelling", orderText + " is forbidden to produce");
        Communication.server.sendMessageToClient(msgObj);

    }

    @Override
    public int next() {
        // TODO Auto-generated method stub
        return 0;
    }

}
