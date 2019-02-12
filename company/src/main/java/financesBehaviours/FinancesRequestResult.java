package financesBehaviours;

import common.AgentDataStore;
import interactors.RequestResult;
import jade.lang.acl.ACLMessage;

public class FinancesRequestResult extends RequestResult {

	public FinancesRequestResult(AgentDataStore dataStore) {
		super(dataStore);
	}

	@Override
	public ACLMessage execute(ACLMessage request) {
		ACLMessage response = request.createReply();
		response.setContent(request.getContent());

		if (!dataStore.getDeadlineResult()) {
			String conversationId = request.getConversationId();
			if ("Order".equals(conversationId)) {
				response.setPerformative(ACLMessage.INFORM);
				this.isDone = true;
				return response;
			} else if ("Materials".equals(conversationId)) {
				response.setPerformative(ACLMessage.INFORM);
				this.isDone = true;
				return response;
			}
		}

		response.setPerformative(ACLMessage.FAILURE);
		this.isDone = false;
		return response;
	}
}
