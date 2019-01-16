package sellingBehaviours;

import interactors.DecisionBehaviour;
import interactors.OrderDataStore;

public class SellingDecisionBehaviour extends DecisionBehaviour {

	private static final long serialVersionUID = 1860338194487186607L;

	public SellingDecisionBehaviour(SellingResponder interactionBehaviour, OrderDataStore dataStore) {
		super(interactionBehaviour);
		this.interactor = new SellingDecision(interactionBehaviour, dataStore);
	}
}
