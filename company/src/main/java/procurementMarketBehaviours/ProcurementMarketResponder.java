package procurementMarketBehaviours;

import interactors.OrderDataStore;
import interactors.ResponderBehaviour;
import jade.core.Agent;
import jade.lang.acl.MessageTemplate;

public class ProcurementMarketResponder extends ResponderBehaviour {

    private static final long serialVersionUID = 8819328566657528097L;

    public static ProcurementMarketRequestResult interactor;
    
    public ProcurementMarketResponder(Agent a, MessageTemplate mt, OrderDataStore dataStore) {
        super(a, mt, dataStore);
        interactor = new ProcurementMarketRequestResult(dataStore);

        registerHandleRequest(new ProcurementMarketDecisionBehaviour(this, dataStore));
        registerPrepareResultNotification(new ProcurementMarketAskBehaviour(this, interactor, dataStore));
//        registerPrepareResultNotification(new ProcurementMarketActivityBehaviour(this, dataStore));
    }

}
