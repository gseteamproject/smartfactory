package procurementMarketBehaviours;

import interactors.AchieveREInitiatorInteractorBehaviour;
import interactors.OrderDataStore;
import interactors.ResponderBehaviour;

public class ReportFinancesInitiatorBehaviour extends AchieveREInitiatorInteractorBehaviour {

	private static final long serialVersionUID = 9100272572803215372L;

	public ReportFinancesInitiatorBehaviour(ResponderBehaviour interactionBehaviour, OrderDataStore dataStore) {
		super(new ReportFinancesInitiator(interactionBehaviour, dataStore));
	}
}
