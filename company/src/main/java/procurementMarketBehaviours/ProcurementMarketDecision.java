package procurementMarketBehaviours;

import common.AgentDataStore;
import communication.MessageObject;
import interactors.Decision;
import interactors.ResponderBehaviour;
import jade.lang.acl.ACLMessage;

public class ProcurementMarketDecision extends Decision {

	private MessageObject msgObj;

	public ProcurementMarketDecision(ResponderBehaviour interactionBehaviour, AgentDataStore dataStore) {
		super(interactionBehaviour, dataStore);
	}

	@Override
	public ACLMessage execute(ACLMessage request) {

		setup(request);

		response = request.createReply();
		response.setContent(request.getContent());
		response.setPerformative(ACLMessage.AGREE);
		response.setSender(interactionBehaviour.getAgent().getAID());

		msgObj = new MessageObject("AgentProcurementMarket", "I will look for materials for " + orderText);
		dataStore.getAgentPlatform().sendMessageToWebClient(msgObj);

		return response;
	}
}
