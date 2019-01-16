package salesMarketBehaviours;

import interactors.OrderDataStore;
import interactors.RequestResult;
import jade.lang.acl.ACLMessage;

public class SalesMarketRequestResult extends RequestResult {

	public SalesMarketRequestResult(OrderDataStore dataStore) {
		super(dataStore);
	}

	@Override
	public ACLMessage execute(ACLMessage request) {
		ACLMessage response = request.createReply();
		response.setContent(request.getContent());
		if (!dataStore.getDeadlineResult()) {
			response.setPerformative(ACLMessage.INFORM);
			this.isDone = true;
		} else {
			response.setPerformative(ACLMessage.FAILURE);
			this.isDone = false;
		}

		return response;
	}
}
