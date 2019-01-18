package financesBehaviours;

import interactors.ActivityBehaviour;
import interactors.OrderDataStore;
import interactors.ResponderBehaviour;
import jade.core.Agent;
import jade.domain.FIPANames;
import jade.proto.AchieveREResponder;

public class FinancesResponder extends ResponderBehaviour {

	private static final long serialVersionUID = 3805964860244663233L;

	public FinancesResponder(Agent a, OrderDataStore dataStore) {
		super(a, AchieveREResponder.createMessageTemplate(FIPANames.InteractionProtocol.FIPA_REQUEST), dataStore);
		interactor = new FinancesRequestResult(dataStore);
		askBehaviour = new FinancesAskBehaviour(this, dataStore);

		registerHandleRequest(new FinancesDecisionBehaviour(this, dataStore));
		registerPrepareResultNotification(new ActivityBehaviour(this));
	}
}
