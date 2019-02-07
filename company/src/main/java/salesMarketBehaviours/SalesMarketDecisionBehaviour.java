package salesMarketBehaviours;

import common.AgentDataStore;
import interactors.DecisionBehaviour;
import interactors.ResponderBehaviour;

public class SalesMarketDecisionBehaviour extends DecisionBehaviour {

	private static final long serialVersionUID = 7972995205178086934L;

	public SalesMarketDecisionBehaviour(ResponderBehaviour interactionBehaviour, AgentDataStore dataStore) {
		super(interactionBehaviour, new SalesMarketDecision(interactionBehaviour, dataStore));
	}
}
