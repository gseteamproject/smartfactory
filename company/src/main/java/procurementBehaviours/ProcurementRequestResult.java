package procurementBehaviours;

import common.AgentDataStore;
import interactors.RequestResult;
import jade.lang.acl.ACLMessage;

public class ProcurementRequestResult extends RequestResult {

	public ProcurementRequestResult(AgentDataStore dataStore) {
		super(dataStore);
	}

	@Override
	public ACLMessage execute(ACLMessage request) {
		ACLMessage response = request.createReply();
		response.setContent(request.getContent());

		if (!dataStore.getDeadlineResult()) {
			if (request.getConversationId() == "Materials") {
				if (dataStore.getIsInMaterialStorage()) {
					response.setPerformative(ACLMessage.INFORM);
					this.isDone = true;
				} else {
					response.setPerformative(ACLMessage.FAILURE);
					this.isDone = false;
				}
			} else if (request.getConversationId() == "Take") {
				if (dataStore.getIsGiven()) {
					response.setPerformative(ACLMessage.INFORM);
					this.isDone = true;
				} else {
					response.setPerformative(ACLMessage.FAILURE);
					this.isDone = false;
				}
			}
		} else {
			response.setPerformative(ACLMessage.FAILURE);
			this.isDone = false;
		}

		return response;
	}
}
