package procurementMarketBehaviours;

import interactors.OrderDataStore;
import jade.core.behaviours.ParallelBehaviour;

public class ProcurementMarketActivityBehaviour extends ParallelBehaviour {

    /**
     * 
     */
    private static final long serialVersionUID = -8533259660402146147L;

    public ProcurementMarketActivityBehaviour(ProcurementMarketResponder interactionBehaviour,
            ProcurementMarketRequestResult interactor, OrderDataStore dataStore) {
        super(interactionBehaviour.getAgent(), WHEN_ANY);

        addSubBehaviour(dataStore.getDeadlineBehaviour());
        // addSubBehaviour(new ProcurementMarketDeadlineBehaviour(interactionBehaviour,
        // interactor, dataStore));
        // addSubBehaviour(new ReportFinancesBehaviour(interactionBehaviour,
        // dataStore));
        // addSubBehaviour(new AuctionInitiator(interactionBehaviour));
        addSubBehaviour(new ProcurementMarketAskBehaviour(interactionBehaviour, interactor, dataStore));
    }
}
