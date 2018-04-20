package procurementMarketBehaviours;

import interactors.OrderDataStore;
import interactors.RequestResult;
import jade.core.AID;
import jade.lang.acl.ACLMessage;

public class ProcurementMarketRequestResult extends RequestResult {

    public ProcurementMarketRequestResult(OrderDataStore dataStore) {
        super(dataStore);
    }

    @Override
    public ACLMessage execute(ACLMessage request) {
        ACLMessage response = request.createReply();
        response.setContent(request.getContent());
        response.setPerformative(ACLMessage.INFORM);
        response.setSender(new AID(("AgentProcurementMarket"), AID.ISLOCALNAME));

        this.isDone = true;

        return response;
    }
}
