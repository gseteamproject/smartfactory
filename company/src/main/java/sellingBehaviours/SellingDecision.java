package sellingBehaviours;

import common.AgentDataStore;
import communication.MessageObject;
import interactors.Decision;
import interactors.ResponderBehaviour;
import jade.lang.acl.ACLMessage;

public class SellingDecision extends Decision {

	public SellingDecision(ResponderBehaviour interactionBehaviour, AgentDataStore dataStore) {
		super(interactionBehaviour, dataStore);
	}

	@Override
	public ACLMessage execute(ACLMessage request) {
		setup(request);

		ACLMessage response = request.createReply();
		response.setContent(request.getContent());
		response.setPerformative(ACLMessage.AGREE);
		response.setSender(interactionBehaviour.getAgent().getAID());

		MessageObject msgObj = null;
		if (request.getConversationId() == "Ask") {
			msgObj = new MessageObject(response, order.getTextOfOrder());
		} else if (request.getConversationId() == "Take") {
			msgObj = new MessageObject(response, order.getTextOfOrder());
		}
		dataStore.getAgentPlatform().sendMessageToWebClient(msgObj);

		return response;
	}
}
