package salesMarketBehaviours;

import interactors.OrderDataStore;
import interactors.ResponderBehaviour;
import jade.core.Agent;
import jade.lang.acl.MessageTemplate;

public class SalesMarketResponder extends ResponderBehaviour {

    private static final long serialVersionUID = 7386418031416044376L;

    public SalesMarketResponder(Agent a, MessageTemplate mt, OrderDataStore dataStore) {
        super(a, mt);
        interactor = new SalesMarketRequestResult(dataStore);
        dataStore.setRequestResult(interactor);
        askBehaviour = new SalesMarketAskBehaviour(this, dataStore);
        setup(dataStore);

        registerHandleRequest(new SalesMarketDecisionBehaviour(this, dataStore));
        // registerPrepareResultNotification(new SalesMarketAskBehaviour(this,
        // interactor, dataStore));
        registerPrepareResultNotification(new SalesMarketActivityBehaviour(this, dataStore));
        // registerPrepareResultNotification(new SalesMarketStartActivityBehaviour(this,
        // interactor, dataStore));
    }
}