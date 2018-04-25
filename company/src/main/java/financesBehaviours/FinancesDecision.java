package financesBehaviours;

import communication.Communication;
import communication.MessageObject;
import interactors.Decision;
import interactors.OrderDataStore;
import jade.core.AID;
import jade.lang.acl.ACLMessage;

public class FinancesDecision extends Decision {

    public FinancesDecision(FinancesResponder interactionBehaviour, OrderDataStore dataStore) {
        super(interactionBehaviour, dataStore);
    }
    
    @Override
    public ACLMessage execute(ACLMessage request) {

        setup(request);

        response = request.createReply();
        response.setContent(request.getContent());
        response.setPerformative(ACLMessage.AGREE);
        response.setSender(new AID(("AgentFinances"), AID.ISLOCALNAME));

        if (request.getConversationId() == "Order") {
            msgObj = new MessageObject("AgentFinances", "has accepted selling of " + orderText);
            Communication.server.sendMessageToClient(msgObj);
        } else if (request.getConversationId() == "Materials") {
            msgObj = new MessageObject("AgentFinances", "has accepted buying of " + orderText);
            Communication.server.sendMessageToClient(msgObj);

        }

        return response;
    }

}