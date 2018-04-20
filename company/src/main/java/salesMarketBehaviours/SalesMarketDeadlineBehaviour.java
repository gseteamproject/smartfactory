package salesMarketBehaviours;

import interactors.DeadlineBehaviour;
import interactors.OrderDataStore;

public class SalesMarketDeadlineBehaviour extends DeadlineBehaviour {

    /**
     * 
     */
    private static final long serialVersionUID = -7011771949303737555L;

    public SalesMarketDeadlineBehaviour(SalesMarketResponder interactionBehaviour, SalesMarketRequestResult interactor,
            OrderDataStore dataStore) {
        super(interactionBehaviour, interactor, dataStore);
        this.interactor = interactor;
    }
}
