package procurementBehaviours;

import interactors.DeadlineBehaviour;
import interactors.OrderDataStore;
import interactors.ResponderBehaviour;
import jade.core.Agent;
import jade.lang.acl.MessageTemplate;

public class ProcurementResponder extends ResponderBehaviour {

    private static final long serialVersionUID = -5804509731381843266L;

    public static ProcurementRequestResult interactor;

    public ProcurementResponder(Agent a, MessageTemplate mt, OrderDataStore dataStore) {
        super(a, mt, dataStore);
        interactor = new ProcurementRequestResult(dataStore);
        deadline = new DeadlineBehaviour(this, interactor, dataStore);
        dataStore.setDeadlineBehaviour(deadline);

        registerHandleRequest(new ProcurementDecisionBehaviour(this, dataStore));
        // registerPrepareResultNotification(new ProcurementAskBehaviour(this,
        // interactor, dataStore));
        registerPrepareResultNotification(new ProcurementActivityBehaviour(this, interactor, dataStore));
    }
}