package sellingBehaviours;

import common.AgentDataStore;
import interactors.ActivityBehaviour;
import interactors.ResponderBehaviour;
import jade.core.Agent;

public class SellingResponder extends ResponderBehaviour {

	private static final long serialVersionUID = -5695904570705958678L;

	public SellingResponder(Agent a, AgentDataStore dataStore) {
		super(a, dataStore);
		requestResult = new SellingRequestResult(dataStore);
		askBehaviour = new SellingAskBehaviour(this, dataStore);

		registerHandleRequest(new SellingDecisionBehaviour(this, dataStore));
		registerPrepareResultNotification(new ActivityBehaviour(this));
	}
}