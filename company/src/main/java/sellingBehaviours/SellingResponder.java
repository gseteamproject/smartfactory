package sellingBehaviours;

import interactors.OrderDataStore;
import interactors.ResponderBehaviour;
import jade.core.Agent;
import jade.lang.acl.MessageTemplate;

public class SellingResponder extends ResponderBehaviour {

    private static final long serialVersionUID = -5695904570705958678L;

    public static SellingRequestResult interactor;
    
    public SellingResponder(Agent a, MessageTemplate mt, OrderDataStore dataStore) {
        super(a, mt, dataStore);
        interactor = new SellingRequestResult(dataStore);

        registerHandleRequest(new SellingDecisionBehaviour(this, dataStore));
        registerPrepareResultNotification(new SellingAskBehaviour(this, interactor, dataStore));
//        registerPrepareResultNotification(new SellingActivityBehaviour(this, dataStore));
    }
}