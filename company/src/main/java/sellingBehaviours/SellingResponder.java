package sellingBehaviours;

import interactors.OrderDataStore;
import interactors.ResponderBehaviour;
import jade.core.Agent;
import jade.lang.acl.MessageTemplate;

public class SellingResponder extends ResponderBehaviour {

	private static final long serialVersionUID = -5695904570705958678L;

	public SellingResponder(Agent a, MessageTemplate mt, OrderDataStore dataStore) {
		super(a, mt);
		interactor = new SellingRequestResult(dataStore);
		dataStore.setRequestResult(interactor);
		askBehaviour = new SellingAskBehaviour(this, dataStore);
		setup(dataStore);

		registerHandleRequest(new SellingDecisionBehaviour(this, dataStore));
		// registerPrepareResultNotification(new SellingAskBehaviour(this, interactor,
		// dataStore));
		registerPrepareResultNotification(new SellingActivityBehaviour(this, dataStore));
	}
}