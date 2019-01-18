package procurementMarketBehaviours;

import communication.Communication;
import communication.MessageObject;
import interactors.Decision;
import interactors.OrderDataStore;
import interactors.ResponderBehaviour;
import jade.lang.acl.ACLMessage;

public class ProcurementMarketDecision extends Decision {

	private MessageObject msgObj;

	public ProcurementMarketDecision(ResponderBehaviour interactionBehaviour, OrderDataStore dataStore) {
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
		Communication.server.sendMessageToClient(msgObj);

		return response;
	}
}
