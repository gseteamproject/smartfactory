package procurementMarketBehaviours;

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

public class ReportFinancesInitiator extends RequestInteractor implements AchieveREInitiatorInteractor {

    private ProcurementMarketResponder interactionBehaviour;
    private String orderText;
    public MessageObject msgObj;

    public ReportFinancesInitiator(ProcurementMarketResponder interactionBehaviour, OrderDataStore dataStore) {
        super(dataStore);
        this.interactionBehaviour = interactionBehaviour;
    }

    @Override
    public Vector<ACLMessage> prepareRequests(ACLMessage request) {
        request = new ACLMessage(ACLMessage.REQUEST);

        String requestedAction = "Materials";
        request.setConversationId(requestedAction);
        request.addReceiver(new AID(("AgentFinances"), AID.ISLOCALNAME));
        request.setProtocol(FIPANames.InteractionProtocol.FIPA_REQUEST);
        request.setContent(dataStore.getRequestMessage().getContent());

        Vector<ACLMessage> l = new Vector<ACLMessage>(1);
        l.addElement(request);

        return l;
    }

    @Override
    public void handleInform(ACLMessage inform) {

        handleResponse(inform);

        Order order = Order.gson.fromJson(inform.getContent(), Order.class);
        orderText = order.getTextOfOrder();

        msgObj = new MessageObject("AgentProcurementMarket", orderText + " is allowed to purchase");
        Communication.server.sendMessageToClient(msgObj);

        ACLMessage msgToPurchase = (ACLMessage) inform.clone();
        dataStore.setSubMessage(msgToPurchase);

        // add order to queue
        Selling.productionQueue.add(order);

        interactionBehaviour.getAgent()
                .addBehaviour(new AuctionInitiator((ProcurementMarketResponder) interactionBehaviour));

    }

    @Override
    public void handleFailure(ACLMessage failure) {

        handleResponse(failure);

        orderText = Order.gson.fromJson(failure.getContent(), Order.class).getTextOfOrder();

        msgObj = new MessageObject("AgentProcurementMarket", orderText + " is forbidden to purchase");
        Communication.server.sendMessageToClient(msgObj);

    }

    @Override
    public void handleAgree(ACLMessage agree) {

        handleResponse(agree);

        orderText = Order.gson.fromJson(agree.getContent(), Order.class).getTextOfOrder();

        msgObj = new MessageObject("AgentProcurementMarket", "Purchase of " + orderText + " is initiated.");
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
