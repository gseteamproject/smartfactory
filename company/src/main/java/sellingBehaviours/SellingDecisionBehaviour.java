package sellingBehaviours;

import interactors.DecisionBehaviour;
import interactors.OrderDataStore;
import interactors.ResponderBehaviour;

public class SellingDecisionBehaviour extends DecisionBehaviour {

	private static final long serialVersionUID = 1860338194487186607L;

	public SellingDecisionBehaviour(ResponderBehaviour interactionBehaviour, OrderDataStore dataStore) {
		super(interactionBehaviour);
		this.interactor = new SellingDecision(interactionBehaviour, dataStore);
	}
}
