package procurementBehaviours;

import interactors.ActivityBehaviour;
import interactors.OrderDataStore;
import interactors.ResponderBehaviour;
import jade.core.Agent;
import jade.lang.acl.MessageTemplate;

public class ProcurementResponder extends ResponderBehaviour {

	private static final long serialVersionUID = -5804509731381843266L;

	public ProcurementResponder(Agent a, MessageTemplate mt, OrderDataStore dataStore) {
		super(a, mt, dataStore);
		interactor = new ProcurementRequestResult(dataStore);
		askBehaviour = new ProcurementAskBehaviour(this, dataStore);

		registerHandleRequest(new ProcurementDecisionBehaviour(this, dataStore));
		registerPrepareResultNotification(new ActivityBehaviour(this));
	}
}