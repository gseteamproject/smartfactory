package productionBehaviours;

import basicAgents.ProductionAgent;
import interactors.OrderDataStore;
import interactors.RequestResult;
import jade.lang.acl.ACLMessage;

public class ProductionRequestResult extends RequestResult {

	private ProductionAgent thisProductionAgent;

	public ProductionRequestResult(OrderDataStore dataStore) {
		super(dataStore);
		thisProductionAgent = (ProductionAgent) dataStore.getThisAgent();
	}

	@Override
	public ACLMessage execute(ACLMessage request) {
		ACLMessage response = request.createReply();
		response.setContent(request.getContent());

		if (!dataStore.getDeadlineResult()) {
			if (thisProductionAgent.isProduced) {
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
