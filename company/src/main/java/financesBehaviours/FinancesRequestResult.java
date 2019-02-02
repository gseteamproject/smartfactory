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
			if (request.getConversationId() == "Order") {
				response.setPerformative(ACLMessage.INFORM);
				this.isDone = true;
			} else if (request.getConversationId() == "Materials") {
				response.setPerformative(ACLMessage.INFORM);
				this.isDone = true;
			}
		} else {
			response.setPerformative(ACLMessage.FAILURE);
			this.isDone = false;
		}

		return response;
	}
}
