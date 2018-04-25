package productionBehaviours;

import interactors.DeadlineBehaviour;
import interactors.OrderDataStore;
import interactors.ResponderBehaviour;
import jade.core.Agent;
import jade.lang.acl.MessageTemplate;

public class ProductionResponder extends ResponderBehaviour {

    private static final long serialVersionUID = -5695904570705958678L;

    public static ProductionRequestResult interactor;

    public ProductionResponder(Agent a, MessageTemplate mt, OrderDataStore dataStore) {
        super(a, mt, dataStore);
        interactor = new ProductionRequestResult(dataStore);
        deadline = new DeadlineBehaviour(this, interactor, dataStore);
        dataStore.setDeadlineBehaviour(deadline);

        registerHandleRequest(new ProductionDecisionBehaviour(this, dataStore));
        // registerPrepareResultNotification(new ProductionAskBehaviour(this,
        // interactor, dataStore));
        registerPrepareResultNotification(new ProductionActivityBehaviour(this, interactor, dataStore));
    }

}
