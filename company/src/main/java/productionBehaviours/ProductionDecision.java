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

		response = request.createReply();
		response.setContent(request.getContent());
		response.setPerformative(ACLMessage.AGREE);
		response.setSender(interactionBehaviour.getAgent().getAID());

		msgObj = new MessageObject("AgentProduction", orderText + " will be produced");
		dataStore.getAgentPlatform().sendMessageToWebClient(msgObj);

		return response;
	}
}
