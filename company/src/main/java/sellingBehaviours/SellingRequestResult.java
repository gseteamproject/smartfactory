package sellingBehaviours;

import basicAgents.SellingAgent;
import interactors.OrderDataStore;
import interactors.RequestResult;
import jade.lang.acl.ACLMessage;

public class SellingRequestResult extends RequestResult {

	private SellingAgent thisSellingAgent;

	public SellingRequestResult(OrderDataStore dataStore) {
		super(dataStore);
		this.thisSellingAgent = (SellingAgent) dataStore.getThisAgent();
	}

    @Override
    public ACLMessage execute(ACLMessage request) {
        ACLMessage response = request.createReply();
        response.setContent(request.getContent());
        // TODO: Need to check if in warehouse here?
        if (!dataStore.getDeadlineResult()) {
            if (request.getConversationId() == "Ask") {
                if (thisSellingAgent.isInWarehouse) {
                    response.setPerformative(ACLMessage.INFORM);
                    this.isDone = true;
                } else {
                    response.setPerformative(ACLMessage.FAILURE);
                    this.isDone = false;
                }
            } else if (request.getConversationId() == "Take") {
                if (thisSellingAgent.isTaken) {
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
