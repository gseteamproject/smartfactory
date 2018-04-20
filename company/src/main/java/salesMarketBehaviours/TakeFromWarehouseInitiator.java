package salesMarketBehaviours;

import java.util.Vector;

import interactors.AchieveREInitiatorInteractor;
import interactors.OrderDataStore;
import interactors.RequestInteractor;
import jade.core.AID;
import jade.domain.FIPANames;
import jade.lang.acl.ACLMessage;

public class TakeFromWarehouseInitiator extends RequestInteractor implements AchieveREInitiatorInteractor {

    private SalesMarketResponder interactionBehaviour;

    public TakeFromWarehouseInitiator(SalesMarketResponder interactionBehaviour, OrderDataStore dataStore) {
        super(dataStore);
        this.interactionBehaviour = interactionBehaviour;
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
    public void handleInform(ACLMessage inform) {

        handleResponse(inform);

        interactionBehaviour.getAgent().addBehaviour(new DeliverToCustomerBehaviour(interactionBehaviour, dataStore));
    }

    @Override
    public void handleFailure(ACLMessage failure) {

        handleResponse(failure);
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
