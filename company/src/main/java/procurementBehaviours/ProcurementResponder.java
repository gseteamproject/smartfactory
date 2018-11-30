package procurementBehaviours;

import interactors.OrderDataStore;
import interactors.ResponderBehaviour;
import jade.core.Agent;
import jade.lang.acl.MessageTemplate;

public class ProcurementResponder extends ResponderBehaviour {

    private static final long serialVersionUID = -5804509731381843266L;

    public ProcurementResponder(Agent a, MessageTemplate mt, OrderDataStore dataStore) {
        super(a, mt);
        interactor = new ProcurementRequestResult(dataStore);
        dataStore.setRequestResult(interactor);
        askBehaviour = new ProcurementAskBehaviour(this, dataStore);
        setup(dataStore);

        registerHandleRequest(new ProcurementDecisionBehaviour(this, dataStore));
        // registerPrepareResultNotification(new ProcurementAskBehaviour(this,
        // interactor, dataStore));
        registerPrepareResultNotification(new ProcurementActivityBehaviour(this, dataStore));
    }
}