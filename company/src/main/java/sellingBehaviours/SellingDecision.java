package sellingBehaviours;

import communication.Communication;
import communication.MessageObject;
import interactors.Decision;
import interactors.OrderDataStore;
import jade.lang.acl.ACLMessage;

public class SellingDecision extends Decision {

    public SellingDecision(SellingResponder interactionBehaviour, OrderDataStore dataStore) {
        super(interactionBehaviour, dataStore);
    }

    @Override
    public ACLMessage execute(ACLMessage request) {

        setup(request);

        response = request.createReply();
        response.setContent(request.getContent());
        response.setPerformative(ACLMessage.AGREE);
        response.setSender(interactionBehaviour.getAgent().getAID());

        // response.setPerformative(ACLMessage.REFUSE);

        /* System.out.println(msgObj.getReceivedMessage()); */

        if (request.getConversationId() == "Ask") {

            msgObj = new MessageObject(response, orderText);
            Communication.server.sendMessageToClient(msgObj);

            /*
             * System.out.println(msgObj.getReceivedMessage());
             * Communication.server.sendMessageToClient("SellingAgent",
             * "[agree] I will check warehouse for " + orderText);
             */
        } else if (request.getConversationId() == "Take") {

            msgObj = new MessageObject(response, orderText);
            Communication.server.sendMessageToClient(msgObj);

            /*
             * System.out.println(msgObj.getReceivedMessage());
             * Communication.server.sendMessageToClient("SellingAgent",
             * "[agree] I will give you " + orderText + " from warehouse");
             */
        }

        return response;
    }
}
