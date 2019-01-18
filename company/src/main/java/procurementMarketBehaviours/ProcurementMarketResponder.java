package procurementMarketBehaviours;

import interactors.ActivityBehaviour;
import interactors.OrderDataStore;
import interactors.ResponderBehaviour;
import jade.core.Agent;
import jade.lang.acl.MessageTemplate;

public class ProcurementMarketResponder extends ResponderBehaviour {

	private static final long serialVersionUID = 8819328566657528097L;

	public ProcurementMarketResponder(Agent a, MessageTemplate mt, OrderDataStore dataStore) {
		super(a, mt, dataStore);
		interactor = new ProcurementMarketRequestResult(dataStore);
		askBehaviour = new ProcurementMarketAskBehaviour(this, dataStore);

		registerHandleRequest(new ProcurementMarketDecisionBehaviour(this, dataStore));
		registerPrepareResultNotification(new ActivityBehaviour(this));
	}
}
