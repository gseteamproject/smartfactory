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
        response.setPerformative(ACLMessage.INFORM);
        this.isDone = true;
        // response.setPerformative(ACLMessage.FAILURE);

        return response;
    }
}
