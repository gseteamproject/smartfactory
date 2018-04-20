package financesBehaviours;

import interactors.OrderDataStore;
import interactors.ResponderBehaviour;
import jade.core.Agent;
import jade.lang.acl.MessageTemplate;

public class FinancesResponder extends ResponderBehaviour {

    private static final long serialVersionUID = 3805964860244663233L;

    public static FinancesRequestResult interactor;
    
    public FinancesResponder(Agent a, MessageTemplate mt, OrderDataStore dataStore) {
        super(a, mt, dataStore);
        interactor = new FinancesRequestResult(dataStore);

        registerHandleRequest(new FinancesDecisionBehaviour(this, dataStore));
        registerPrepareResultNotification(new FinancesAskBehaviour(this, interactor, dataStore));
    }
}
