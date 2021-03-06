package financesBehaviours;

import common.AgentDataStore;
import communication.MessageObject;
import interactors.Decision;
import interactors.ResponderBehaviour;
import jade.lang.acl.ACLMessage;

public class FinancesDecision extends Decision {

	public FinancesDecision(ResponderBehaviour interactionBehaviour, AgentDataStore dataStore) {
		super(interactionBehaviour, dataStore);
	}

	@Override
	public ACLMessage execute(ACLMessage request) {
		setup(request);

		MessageObject msgObj = null;
		if (request.getConversationId() == "Order") {
			msgObj = new MessageObject("AgentFinances", "has accepted selling of " + order.getTextOfOrder());
		} else if (request.getConversationId() == "Materials") {
			msgObj = new MessageObject("AgentFinances", "has accepted buying of " + order.getTextOfOrder());
		}
		dataStore.getAgentPlatform().sendMessageToWebClient(msgObj);

		ACLMessage response = request.createReply();
		response.setContent(request.getContent());
		response.setPerformative(ACLMessage.AGREE);
		response.setSender(interactionBehaviour.getAgent().getAID());
		return response;
	}
}
