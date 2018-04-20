package procurementMarketBehaviours;

import interactors.AchieveREInitiatorInteractorBehaviour;
import interactors.OrderDataStore;

public class ReportFinancesInitiatorBehaviour extends AchieveREInitiatorInteractorBehaviour {

    /**
     * 
     */
    private static final long serialVersionUID = 9100272572803215372L;

    public ReportFinancesInitiatorBehaviour(ProcurementMarketResponder interactionBehaviour, OrderDataStore dataStore) {
        super(new ReportFinancesInitiator(interactionBehaviour, dataStore));
    }
}
