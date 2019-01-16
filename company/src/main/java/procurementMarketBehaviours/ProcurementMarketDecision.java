package procurementMarketBehaviours;

import communication.Communication;
import communication.MessageObject;
import interactors.Decision;
import interactors.OrderDataStore;
import jade.lang.acl.ACLMessage;

public class ProcurementMarketDecision extends Decision {

	private MessageObject msgObj;

	public ProcurementMarketDecision(ProcurementMarketResponder interactionBehaviour, OrderDataStore dataStore) {
		super(interactionBehaviour, dataStore);
		// TODO Auto-generated constructor stub
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
