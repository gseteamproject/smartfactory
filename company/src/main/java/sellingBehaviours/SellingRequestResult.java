package sellingBehaviours;

import common.AgentDataStore;
import interactors.RequestResult;
import jade.lang.acl.ACLMessage;

public class SellingRequestResult extends RequestResult {

	public SellingRequestResult(AgentDataStore dataStore) {
		super(dataStore);
	}

	@Override
	public ACLMessage execute(ACLMessage request) {
		ACLMessage response = request.createReply();
		response.setContent(request.getContent());

		if (!dataStore.getDeadlineResult()) {
			String conversationId = request.getConversationId();
			if ("Ask".equals(conversationId)) {
				if (dataStore.getIsInWarehouse()) {
					response.setPerformative(ACLMessage.INFORM);
					this.isDone = true;
				} else {
					response.setPerformative(ACLMessage.FAILURE);
					this.isDone = false;
				}
				return response;
			} else if ("Take".equals(conversationId)) {
				if (dataStore.getIsTaken()) {
					response.setPerformative(ACLMessage.INFORM);
					this.isDone = true;
				} else {
					response.setPerformative(ACLMessage.FAILURE);
					this.isDone = false;
				}
				return response;
			}
		}

		response.setPerformative(ACLMessage.FAILURE);
		this.isDone = false;
		return response;
	}
}
