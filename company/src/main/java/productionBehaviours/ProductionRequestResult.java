package productionBehaviours;

import common.AgentDataStore;
import interactors.RequestResult;
import jade.lang.acl.ACLMessage;

public class ProductionRequestResult extends RequestResult {

	public ProductionRequestResult(AgentDataStore dataStore) {
		super(dataStore);
	}

	@Override
	public ACLMessage execute(ACLMessage request) {
		ACLMessage response = request.createReply();
		response.setContent(request.getContent());

		if (!dataStore.getDeadlineResult()) {
			if (dataStore.getIsProduced()) {
				response.setPerformative(ACLMessage.INFORM);
				this.isDone = true;
			} else {
				response.setPerformative(ACLMessage.FAILURE);
				this.isDone = false;
			}
		} else {
			response.setPerformative(ACLMessage.FAILURE);
			this.isDone = false;
		}

		return response;
	}
}
