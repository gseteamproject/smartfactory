package procurementMarketBehaviours;

import common.AgentDataStore;
import communication.MessageObject;
import interactors.Decision;
import interactors.ResponderBehaviour;
import jade.lang.acl.ACLMessage;

public class ProcurementMarketDecision extends Decision {

	public ProcurementMarketDecision(ResponderBehaviour interactionBehaviour, AgentDataStore dataStore) {
		super(interactionBehaviour, dataStore);
	}

	@Override
	public ACLMessage execute(ACLMessage request) {
		setup(request);

		MessageObject msgObj = new MessageObject("AgentProcurementMarket",
				"I will look for materials for " + order.getTextOfOrder());
		dataStore.getAgentPlatform().sendMessageToWebClient(msgObj);

		ACLMessage response = request.createReply();
		response.setContent(request.getContent());
		response.setPerformative(ACLMessage.AGREE);
		response.setSender(interactionBehaviour.getAgent().getAID());
		return response;
	}
}
