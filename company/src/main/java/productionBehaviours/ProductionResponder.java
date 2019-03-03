package productionBehaviours;

import common.AgentDataStore;
import interactors.ActivityBehaviour;
import interactors.ResponderBehaviour;
import jade.core.Agent;

public class ProductionResponder extends ResponderBehaviour {

	private static final long serialVersionUID = -5695904570705958678L;

	public ProductionResponder(Agent a, AgentDataStore dataStore) {
		super(a, dataStore);
		requestResult = new ProductionRequestResult(dataStore);
		askBehaviour = new ProductionAskBehaviour(this, dataStore);

		registerHandleRequest(new ProductionDecisionBehaviour(this, dataStore));
		registerPrepareResultNotification(new ActivityBehaviour(this));
	}
}
