package procurementBehaviours;

import common.AgentDataStore;
import interactors.DecisionBehaviour;
import interactors.ResponderBehaviour;

public class ProcurementDecisionBehaviour extends DecisionBehaviour {

	private static final long serialVersionUID = -9182852661127360232L;

	public ProcurementDecisionBehaviour(ResponderBehaviour interactionBehaviour, AgentDataStore dataStore) {
		super(interactionBehaviour);
		this.interactor = new ProcurementDecision(interactionBehaviour, dataStore);
	}
}
