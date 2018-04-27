package financesBehaviours;

import interactors.OrderDataStore;
import jade.core.behaviours.ParallelBehaviour;

public class FinancesActivityBehaviour extends ParallelBehaviour {

    /**
     * 
     */
    private static final long serialVersionUID = 6418667700416071499L;

    public FinancesActivityBehaviour(FinancesResponder interactionBehaviour, OrderDataStore dataStore) {
        super(interactionBehaviour.getAgent(), WHEN_ANY);

        addSubBehaviour(dataStore.getDeadlineBehaviour());
        addSubBehaviour(dataStore.getAskBehaviour());
        // addSubBehaviour(new FinancesDeadlineBehaviour(interactionBehaviour,
        // interactor, dataStore));

    }

}
