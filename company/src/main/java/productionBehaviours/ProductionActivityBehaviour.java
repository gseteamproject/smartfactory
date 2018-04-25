package productionBehaviours;

import interactors.OrderDataStore;
import jade.core.behaviours.ParallelBehaviour;

public class ProductionActivityBehaviour extends ParallelBehaviour {

    /**
     * 
     */
    private static final long serialVersionUID = -6703040253614653144L;

    public ProductionActivityBehaviour(ProductionResponder interactionBehaviour, ProductionRequestResult interactor,
            OrderDataStore dataStore) {
        super(interactionBehaviour.getAgent(), WHEN_ANY);

        addSubBehaviour(dataStore.getDeadlineBehaviour());
        // addSubBehaviour(new ProductionDeadlineBehaviour(interactionBehaviour,
        // interactor, dataStore));
        addSubBehaviour(new ProductionAskBehaviour(interactionBehaviour, interactor, dataStore));
        // addSubBehaviour(new AskForMaterialsBehaviour(interactionBehaviour,
        // dataStore));
    }
}
