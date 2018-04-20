package sellingBehaviours;

import interactors.DeadlineBehaviour;
import interactors.OrderDataStore;

public class SellingDeadlineBehaviour extends DeadlineBehaviour {

    /**
     * 
     */
    private static final long serialVersionUID = 3277018524589680071L;

    public SellingDeadlineBehaviour(SellingResponder interactionBehaviour, SellingRequestResult interactor,
            OrderDataStore dataStore) {
        super(interactionBehaviour, interactor, dataStore);
        this.interactor = interactor;
    }
}
