package productionBehaviours;

import common.AgentDataStore;
import communication.MessageObject;
import interactors.Decision;
import interactors.ResponderBehaviour;
import jade.lang.acl.ACLMessage;

public class ProductionDecision extends Decision {

	public ProductionDecision(ResponderBehaviour interactionBehaviour, AgentDataStore dataStore) {
		super(interactionBehaviour, dataStore);
	}

	@Override
	public ACLMessage execute(ACLMessage request) {
		setup(request);

		MessageObject msgObj = new MessageObject("AgentProduction", order.getTextOfOrder() + " will be produced");
		dataStore.getAgentPlatform().sendMessageToWebClient(msgObj);

		ACLMessage response = request.createReply();
		response.setContent(request.getContent());
		response.setPerformative(ACLMessage.AGREE);
		response.setSender(interactionBehaviour.getAgent().getAID());
		return response;
	}
}
