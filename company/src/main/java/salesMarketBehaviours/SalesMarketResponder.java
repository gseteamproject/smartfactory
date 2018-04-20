package salesMarketBehaviours;

import interactors.OrderDataStore;
import interactors.ResponderBehaviour;
import jade.core.Agent;
import jade.lang.acl.MessageTemplate;

public class SalesMarketResponder extends ResponderBehaviour {

    /**
     * 
     */
    private static final long serialVersionUID = 7386418031416044376L;
    
    public static SalesMarketRequestResult interactor;

    public SalesMarketResponder(Agent a, MessageTemplate mt, OrderDataStore dataStore) {
        super(a, mt, dataStore);
        interactor = new SalesMarketRequestResult(dataStore);

        registerHandleRequest(new SalesMarketDecisionBehaviour(this, dataStore));
//        registerPrepareResultNotification(new SalesMarketAskBehaviour(this, interactor, dataStore));
        registerPrepareResultNotification(new SalesMarketActivityBehaviour(this, interactor, dataStore));
    }
}