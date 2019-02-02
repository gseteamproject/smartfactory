package salesMarketBehaviours;

import basicClasses.CrossAgentData;
import common.AgentDataStore;
import communication.MessageObject;
import interactors.Decision;
import interactors.ResponderBehaviour;
import jade.lang.acl.ACLMessage;

public class SalesMarketDecision extends Decision {

	public SalesMarketDecision(ResponderBehaviour interactionBehaviour, AgentDataStore dataStore) {
		super(interactionBehaviour, dataStore);
	}

	@Override
	public ACLMessage execute(ACLMessage request) {
		setup(request);

		MessageObject msgObj = new MessageObject(request, "has ordered " + order.getTextOfOrder());
		dataStore.getAgentPlatform().sendMessageToWebClient(msgObj);

		// Agent should send agree or refuse
		ACLMessage response = request.createReply();
		response.setContent(request.getContent());
		response.setSender(interactionBehaviour.getAgent().getAID());

		if (!CrossAgentData.orderQueue.contains(order)) {
			response.setPerformative(ACLMessage.AGREE);
			msgObj = new MessageObject(response, "has accepted order of " + order.getTextOfOrder());
			dataStore.getAgentPlatform().sendMessageToWebClient(msgObj);
		} else {
			response.setPerformative(ACLMessage.REFUSE);
			msgObj = new MessageObject(response, "has rejected order of " + order.getTextOfOrder());
			dataStore.getAgentPlatform().sendMessageToWebClient(msgObj);
		}

		return response;
	}
}
