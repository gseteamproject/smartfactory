package procurementBehaviours;

import communication.Communication;
import communication.MessageObject;
import interactors.Decision;
import interactors.OrderDataStore;
import jade.lang.acl.ACLMessage;

public class ProcurementDecision extends Decision {
    private MessageObject msgObj;

    public ProcurementDecision(ProcurementResponder interactionBehaviour, OrderDataStore dataStore) {
        super(interactionBehaviour, dataStore);
    }

    @Override
    public ACLMessage execute(ACLMessage request) {

        setup(request);

        response = request.createReply();
        response.setContent(request.getContent());
        response.setPerformative(ACLMessage.AGREE);

        if (request.getConversationId() == "Materials") {
            msgObj = new MessageObject("AgentProcurement",
                    "I will check materialStorage for materials for " + orderText);
            Communication.server.sendMessageToClient(msgObj);

            /*
             * System.out.
             * println("ProcurementAgent: [request] ProductionAgent asks for materials for "
             * + orderText); System.out.
             * println("ProcurementAgent: [agree] I will check materialStorage for materials for "
             * + orderText);
             */
        } else if (request.getConversationId() == "Take") {
            msgObj = new MessageObject("AgentProcurement",
                    "I will give materials for " + orderText + " from materialStorage");
            Communication.server.sendMessageToClient(msgObj);

            /*
             * System.out.
             * println("ProcurementAgent: [request] ProductionAgent wants to get materials for "
             * + orderText + " from materialStorage"); System.out.println(
             * "ProcurementAgent: [agree] I will give you materials for " + orderText +
             * " from materialStorage");
             */
        }

        return response;
    }

}