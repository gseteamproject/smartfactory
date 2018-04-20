package productionBehaviours;

import interactors.DeadlineBehaviour;
import interactors.OrderDataStore;

public class ProductionDeadlineBehaviour extends DeadlineBehaviour {

    public ProductionDeadlineBehaviour(ProductionResponder interactionBehaviour, ProductionRequestResult interactor,
            OrderDataStore dataStore) {
        super(interactionBehaviour, interactor, dataStore);
        this.interactor = interactor;
    }

    private static final long serialVersionUID = 9050743659839198854L;
}