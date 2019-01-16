package procurementBehaviours;

import basicAgents.ProcurementAgent;
import interactors.OrderDataStore;
import interactors.RequestResult;
import jade.lang.acl.ACLMessage;

public class ProcurementRequestResult extends RequestResult {

	private ProcurementAgent thisProcurementAgent;

	public ProcurementRequestResult(OrderDataStore dataStore) {
		super(dataStore);
		thisProcurementAgent = (ProcurementAgent) dataStore.getThisAgent();
	}

	@Override
	public ACLMessage execute(ACLMessage request) {
		ACLMessage response = request.createReply();
		response.setContent(request.getContent());

		if (!dataStore.getDeadlineResult()) {
			if (request.getConversationId() == "Materials") {
				if (thisProcurementAgent.isInMaterialStorage) {
					response.setPerformative(ACLMessage.INFORM);
					this.isDone = true;
				} else {
					response.setPerformative(ACLMessage.FAILURE);
					this.isDone = false;
				}
			} else if (request.getConversationId() == "Take") {
				if (thisProcurementAgent.isGiven) {
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
