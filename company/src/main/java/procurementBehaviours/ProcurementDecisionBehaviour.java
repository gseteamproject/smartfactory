package procurementBehaviours;

import interactors.DecisionBehaviour;
import interactors.OrderDataStore;
import interactors.ResponderBehaviour;

public class ProcurementDecisionBehaviour extends DecisionBehaviour {

	private static final long serialVersionUID = -9182852661127360232L;

	public ProcurementDecisionBehaviour(ResponderBehaviour interactionBehaviour, OrderDataStore dataStore) {
		super(interactionBehaviour);
		this.interactor = new ProcurementDecision(interactionBehaviour, dataStore);
	}
}
