package procurementMarketBehaviours;

import common.AgentDataStore;
import interactors.ActivityBehaviour;
import interactors.ResponderBehaviour;
import jade.core.Agent;

public class ProcurementMarketResponder extends ResponderBehaviour {

	private static final long serialVersionUID = 8819328566657528097L;

	public ProcurementMarketResponder(Agent a, AgentDataStore dataStore) {
		super(a, dataStore);
		requestResult = new ProcurementMarketRequestResult(dataStore);
		askBehaviour = new ProcurementMarketAskBehaviour(this, dataStore);

		registerHandleRequest(new ProcurementMarketDecisionBehaviour(this, dataStore));
		registerPrepareResultNotification(new ActivityBehaviour(this));
	}
}
