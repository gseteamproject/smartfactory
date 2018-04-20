package financesBehaviours;

import interactors.DeadlineBehaviour;
import interactors.OrderDataStore;
import jade.core.behaviours.ParallelBehaviour;

public class FinancesActivityBehaviour extends ParallelBehaviour {

    /**
     * 
     */
    private static final long serialVersionUID = 6418667700416071499L;

    public FinancesActivityBehaviour(FinancesResponder interactionBehaviour, FinancesRequestResult interactor, OrderDataStore dataStore) {
        super(interactionBehaviour.getAgent(), WHEN_ANY);

//        addSubBehaviour(new FinancesAskBehaviour(interactionBehaviour, interactor, dataStore));
        addSubBehaviour(new DeadlineBehaviour((FinancesResponder) interactionBehaviour, (FinancesRequestResult) interactor, dataStore));
//        addSubBehaviour(new FinancesDeadlineBehaviour(interactionBehaviour, interactor, dataStore));
        if (dataStore.getRequestMessage().getConversationId() == "Order") {
            addSubBehaviour(new TransferMoneyToBank((FinancesResponder) interactionBehaviour, dataStore));            
        } else if (dataStore.getRequestMessage().getConversationId() == "Materials") {
            addSubBehaviour(new TransferMoneyFromBank((FinancesResponder) interactionBehaviour, dataStore));            
        }
    }

}
