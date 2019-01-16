package salesMarketBehaviours;

import interactors.DecisionBehaviour;
import interactors.OrderDataStore;

public class SalesMarketDecisionBehaviour extends DecisionBehaviour {

	private static final long serialVersionUID = 7972995205178086934L;

	public SalesMarketDecisionBehaviour(SalesMarketResponder interactionBehaviour, OrderDataStore dataStore) {
		super(interactionBehaviour);
		this.interactor = new SalesMarketDecision(interactionBehaviour, dataStore);
	}
}
