package financesBehaviours;

import interactors.DecisionBehaviour;
import interactors.OrderDataStore;

public class FinancesDecisionBehaviour extends DecisionBehaviour {

	private static final long serialVersionUID = -1855907456253553568L;

	public FinancesDecisionBehaviour(FinancesResponder interactionBehaviour, OrderDataStore dataStore) {
		super(interactionBehaviour);
		this.interactor = new FinancesDecision(interactionBehaviour, dataStore);
	}
}
