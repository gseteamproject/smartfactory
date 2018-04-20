package procurementBehaviours;

import java.util.Vector;

import basicAgents.Procurement;
import basicClasses.Order;
import communication.Communication;
import communication.MessageObject;
import interactors.AchieveREInitiatorInteractor;
import interactors.OrderDataStore;
import interactors.RequestInteractor;
import jade.core.AID;
import jade.domain.FIPANames;
import jade.lang.acl.ACLMessage;

public class AskForAuctionInitiator extends RequestInteractor implements AchieveREInitiatorInteractor {

    private ProcurementResponder interactionBehaviour;
    private ProcurementRequestResult interactor;
    private String orderText;
    public MessageObject msgObj;

    public AskForAuctionInitiator(ProcurementResponder interactionBehaviour, OrderDataStore dataStore) {
        super(dataStore);
        this.interactionBehaviour = interactionBehaviour;
        this.interactor = ProcurementResponder.interactor;
    }

    @Override
    public Vector<ACLMessage> prepareRequests(ACLMessage request) {
        request = new ACLMessage(ACLMessage.REQUEST);

        String requestedAction = "Materials";
        request.setConversationId(requestedAction);
        request.addReceiver(new AID(("AgentProcurementMarket"), AID.ISLOCALNAME));
        request.setProtocol(FIPANames.InteractionProtocol.FIPA_REQUEST);
        request.setContent(dataStore.getRequestMessage().getContent());

        Vector<ACLMessage> l = new Vector<ACLMessage>(1);
        l.addElement(request);
        return l;
    }

    @Override
    public void handleInform(ACLMessage inform) {
        
        orderText = Order.gson.fromJson(inform.getContent(), Order.class).getTextOfOrder();
        msgObj = new MessageObject(inform, "received [inform] order " + orderText + " is delivered to materialStorage");
        Communication.server.sendMessageToClient(msgObj);

       /* System.out.println("ProcurementAgent: received [inform] " + orderText + " is delivered to materialStorage");
        Communication.server.sendMessageToClient("ProcurementAgent",
                "received [inform] " + orderText + " is delivered to materialStorage");*/

        Procurement.isInMaterialStorage = true;
        interactor.execute(interactionBehaviour.getRequest());
    }

    @Override
    public void handleFailure(ACLMessage failure) {
        
        orderText = Order.gson.fromJson(failure.getContent(), Order.class).getTextOfOrder();
        msgObj = new MessageObject(failure, "received [failure] order " + orderText + " was not purchased");
        Communication.server.sendMessageToClient(msgObj);

       /* System.out.println("ProcurementAgent: received [failure] were not purchased");
        Communication.server.sendMessageToClient("ProcurementAgent", "received [failure] were not purchased");*/

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
