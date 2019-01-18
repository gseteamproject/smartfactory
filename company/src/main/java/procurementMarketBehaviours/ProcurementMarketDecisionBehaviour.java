package procurementMarketBehaviours;

import interactors.DecisionBehaviour;
import interactors.OrderDataStore;
import interactors.ResponderBehaviour;

public class ProcurementMarketDecisionBehaviour extends DecisionBehaviour {

	private static final long serialVersionUID = -7653878670883427299L;

	public ProcurementMarketDecisionBehaviour(ResponderBehaviour interactionBehaviour, OrderDataStore dataStore) {
		super(interactionBehaviour);
		this.interactor = new ProcurementMarketDecision(interactionBehaviour, dataStore);
	}
}
