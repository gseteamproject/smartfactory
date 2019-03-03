package financesBehaviours;

import common.AgentDataStore;
import interactors.ActivityBehaviour;
import interactors.ResponderBehaviour;
import jade.core.Agent;

public class FinancesResponder extends ResponderBehaviour {

	private static final long serialVersionUID = 3805964860244663233L;

	public FinancesResponder(Agent a, AgentDataStore dataStore) {
		super(a, dataStore);
		requestResult = new FinancesRequestResult(dataStore);
		askBehaviour = new FinancesAskBehaviour(this, dataStore);

		registerHandleRequest(new FinancesDecisionBehaviour(this, dataStore));
		registerPrepareResultNotification(new ActivityBehaviour(this));
	}
}
