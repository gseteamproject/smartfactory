package financesBehaviours;

import interactors.DeadlineBehaviour;
import interactors.OrderDataStore;

public class FinancesDeadlineBehaviour extends DeadlineBehaviour {

    public FinancesDeadlineBehaviour(FinancesResponder interactionBehaviour, FinancesRequestResult interactor,
            OrderDataStore dataStore) {
        super(interactionBehaviour, interactor, dataStore);
        this.interactor = interactor;
    }

    private static final long serialVersionUID = 6309381841361216668L;
}
