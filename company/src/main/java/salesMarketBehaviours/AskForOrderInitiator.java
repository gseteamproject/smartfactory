package salesMarketBehaviours;

import java.util.Vector;

import interactors.AchieveREInitiatorInteractor;
import interactors.OrderDataStore;
import interactors.RequestInteractor;
import jade.core.AID;
import jade.domain.FIPANames;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;

public class AskForOrderInitiator extends RequestInteractor implements AchieveREInitiatorInteractor {

    private SalesMarketResponder interactionBehaviour;

    public AskForOrderInitiator(SalesMarketResponder interactionBehaviour, OrderDataStore dataStore) {
        super(dataStore);
        this.interactionBehaviour = interactionBehaviour;
    }

    @Override
    public Vector<ACLMessage> prepareRequests(ACLMessage request) {
        request = new ACLMessage(ACLMessage.REQUEST);

        String requestedAction = "Ask";
        request.addReceiver(new AID(("AgentSelling"), AID.ISLOCALNAME));
        setup(request, requestedAction);

        return l;
    }

    @Override
    public void handleInform(ACLMessage inform) {

        handleResponse(inform);

        interactionBehaviour.getAgent().addBehaviour(new TakeFromWarehouseBehaviour(interactionBehaviour, dataStore));
    }

    @Override
    public void handleFailure(ACLMessage failure) {

        handleResponse(failure);

        MessageTemplate temp = MessageTemplate.MatchProtocol(FIPANames.InteractionProtocol.FIPA_REQUEST);
        MessageTemplate infTemp = MessageTemplate.and(temp, MessageTemplate.MatchPerformative(ACLMessage.INFORM));
        infTemp = MessageTemplate.and(infTemp, MessageTemplate.MatchConversationId("Ask"));
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
