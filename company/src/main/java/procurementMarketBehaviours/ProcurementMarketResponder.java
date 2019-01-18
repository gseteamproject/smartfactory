package procurementMarketBehaviours;

import interactors.ActivityBehaviour;
import interactors.OrderDataStore;
import interactors.ResponderBehaviour;
import jade.core.Agent;
import jade.domain.FIPANames;
import jade.proto.AchieveREResponder;

public class ProcurementMarketResponder extends ResponderBehaviour {

	private static final long serialVersionUID = 8819328566657528097L;

	public ProcurementMarketResponder(Agent a, OrderDataStore dataStore) {
		super(a, AchieveREResponder.createMessageTemplate(FIPANames.InteractionProtocol.FIPA_REQUEST), dataStore);
		interactor = new ProcurementMarketRequestResult(dataStore);
		askBehaviour = new ProcurementMarketAskBehaviour(this, dataStore);

		registerHandleRequest(new ProcurementMarketDecisionBehaviour(this, dataStore));
		registerPrepareResultNotification(new ActivityBehaviour(this));
	}
}
