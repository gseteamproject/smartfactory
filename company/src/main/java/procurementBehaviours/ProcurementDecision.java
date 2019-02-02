package procurementBehaviours;

import common.AgentDataStore;
import communication.MessageObject;
import interactors.Decision;
import interactors.ResponderBehaviour;
import jade.lang.acl.ACLMessage;

public class ProcurementDecision extends Decision {

	private MessageObject msgObj;

	public ProcurementDecision(ResponderBehaviour interactionBehaviour, AgentDataStore dataStore) {
		super(interactionBehaviour, dataStore);
	}

	@Override
	public ACLMessage execute(ACLMessage request) {
		setup(request);

		if (request.getConversationId() == "Materials") {
			msgObj = new MessageObject("AgentProcurement",
					"I will check materialStorage for materials for " + order.getTextOfOrder());
		} else if (request.getConversationId() == "Take") {
			msgObj = new MessageObject("AgentProcurement",
					"I will give materials for " + order.getTextOfOrder() + " from materialStorage");
		}
		dataStore.getAgentPlatform().sendMessageToWebClient(msgObj);

		ACLMessage response = request.createReply();
		response.setContent(request.getContent());
		response.setPerformative(ACLMessage.AGREE);
		return response;
	}
}
