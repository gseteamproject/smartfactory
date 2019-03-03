package salesMarketBehaviours;

import common.AgentDataStore;
import interactors.ActivityBehaviour;
import interactors.ResponderBehaviour;
import jade.core.Agent;

public class SalesMarketResponder extends ResponderBehaviour {

	private static final long serialVersionUID = 7386418031416044376L;

	public SalesMarketResponder(Agent a, AgentDataStore dataStore) {
		super(a, dataStore);
		requestResult = new SalesMarketRequestResult(dataStore);
		askBehaviour = new SalesMarketAskBehaviour(this, dataStore);

		registerHandleRequest(new SalesMarketDecisionBehaviour(this, dataStore));
		registerPrepareResultNotification(new ActivityBehaviour(this));
	}
}