package salesMarketBehaviours;

import basicClasses.CrossAgentData;
import basicClasses.Order;
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
		Order order = Order.gson.fromJson(request.getContent(), Order.class);
		orderText = order.getTextOfOrder();

		MessageObject msgObj = new MessageObject(request, "has ordered " + orderText);
		dataStore.getAgentPlatform().sendMessageToWebClient(msgObj);

		setup(request);

		// Agent should send agree or refuse
		response = request.createReply();
		response.setContent(request.getContent());
		response.setSender(interactionBehaviour.getAgent().getAID());

		if (!CrossAgentData.orderQueue.contains(order)) {
			response.setPerformative(ACLMessage.AGREE);
			msgObj = new MessageObject(response, "has accepted order of " + orderText);
			dataStore.getAgentPlatform().sendMessageToWebClient(msgObj);
		} else {
			response.setPerformative(ACLMessage.REFUSE);
			msgObj = new MessageObject(response, "has rejected order of " + orderText);
			dataStore.getAgentPlatform().sendMessageToWebClient(msgObj);
		}

		return response;
	}
}
