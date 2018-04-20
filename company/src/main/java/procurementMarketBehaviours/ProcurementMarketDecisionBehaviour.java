package procurementMarketBehaviours;

import interactors.DecisionBehaviour;
import interactors.OrderDataStore;

public class ProcurementMarketDecisionBehaviour extends DecisionBehaviour {

    public ProcurementMarketDecisionBehaviour(ProcurementMarketResponder interactionBehaviour, OrderDataStore dataStore) {
        super(interactionBehaviour);
        this.interactor = new ProcurementMarketDecision(interactionBehaviour, dataStore);
    }

    /**
     * 
     */
    private static final long serialVersionUID = -7653878670883427299L;

}
