package productionBehaviours;

import interactors.DeadlineBehaviour;
import interactors.OrderDataStore;
import jade.core.behaviours.ParallelBehaviour;

public class ProductionActivityBehaviour extends ParallelBehaviour {

    /**
     * 
     */
    private static final long serialVersionUID = -6703040253614653144L;

    // TODO: put this into DataStore

    public ProductionActivityBehaviour(ProductionResponder interactionBehaviour, ProductionRequestResult interactor, OrderDataStore dataStore) {
        super(interactionBehaviour.getAgent(), WHEN_ANY);

        // TODO: Should I remove Ask????

        addSubBehaviour(new DeadlineBehaviour((ProductionResponder) interactionBehaviour, (ProductionRequestResult) interactor, dataStore));
//        addSubBehaviour(new ProductionDeadlineBehaviour(interactionBehaviour, interactor, dataStore));
        addSubBehaviour(new AskForMaterialsBehaviour((ProductionResponder) interactionBehaviour, dataStore));
        // addSubBehaviour(new AskForMaterialsBehaviour(interactionBehaviour,
        // dataStore));
        }
}
