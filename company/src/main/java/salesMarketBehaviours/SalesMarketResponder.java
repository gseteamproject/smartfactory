package salesMarketBehaviours;

import interactors.ActivityBehaviour;
import interactors.OrderDataStore;
import interactors.ResponderBehaviour;
import jade.core.Agent;
import jade.domain.FIPANames;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;

public class SalesMarketResponder extends ResponderBehaviour {

	private static final long serialVersionUID = 7386418031416044376L;

	public SalesMarketResponder(Agent a, OrderDataStore dataStore) {
		super(a, MessageTemplate.and(MessageTemplate.MatchProtocol(FIPANames.InteractionProtocol.FIPA_REQUEST),
				MessageTemplate.MatchPerformative(ACLMessage.REQUEST)), dataStore);
		interactor = new SalesMarketRequestResult(dataStore);
		askBehaviour = new SalesMarketAskBehaviour(this, dataStore);

		registerHandleRequest(new SalesMarketDecisionBehaviour(this, dataStore));
		registerPrepareResultNotification(new ActivityBehaviour(this));
	}
}