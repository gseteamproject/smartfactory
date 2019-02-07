package financesBehaviours;

import common.AgentDataStore;
import interactors.DecisionBehaviour;
import interactors.ResponderBehaviour;

public class FinancesDecisionBehaviour extends DecisionBehaviour {

	private static final long serialVersionUID = -1855907456253553568L;

	public FinancesDecisionBehaviour(ResponderBehaviour interactionBehaviour, AgentDataStore dataStore) {
		super(interactionBehaviour, new FinancesDecision(interactionBehaviour, dataStore));
	}
}
