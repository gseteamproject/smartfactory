package procurementBehaviours;

import common.AgentDataStore;
import interactors.ActivityBehaviour;
import interactors.ResponderBehaviour;
import jade.core.Agent;
import jade.domain.FIPANames;
import jade.proto.AchieveREResponder;

public class ProcurementResponder extends ResponderBehaviour {

	private static final long serialVersionUID = -5804509731381843266L;

	public ProcurementResponder(Agent a, AgentDataStore dataStore) {
		super(a, AchieveREResponder.createMessageTemplate(FIPANames.InteractionProtocol.FIPA_REQUEST), dataStore);
		requestResult = new ProcurementRequestResult(dataStore);
		askBehaviour = new ProcurementAskBehaviour(this, dataStore);

		registerHandleRequest(new ProcurementDecisionBehaviour(this, dataStore));
		registerPrepareResultNotification(new ActivityBehaviour(this));
	}
}