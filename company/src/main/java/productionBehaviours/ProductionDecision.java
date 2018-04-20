package productionBehaviours;

import communication.Communication;
import communication.MessageObject;
import interactors.Decision;
import interactors.OrderDataStore;
import jade.core.AID;
import jade.lang.acl.ACLMessage;

public class ProductionDecision extends Decision {

    public ProductionDecision(ProductionResponder interactionBehaviour, OrderDataStore dataStore) {
        super(interactionBehaviour, dataStore);

    }

    @Override
    public ACLMessage execute(ACLMessage request) {

        setup(request);

        response = request.createReply();
        response.setContent(request.getContent());
        response.setPerformative(ACLMessage.AGREE);
        response.setSender(new AID(("AgentProduction"), AID.ISLOCALNAME));

        msgObj = new MessageObject("AgentProduction", orderText + " will be produced");
        Communication.server.sendMessageToClient(msgObj);
        /*
         * System.out.println("ProductionAgent: [agree] I will produce " + orderText);
         */

        return response;
    }

}
