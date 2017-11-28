package smartfactory.behaviours.production;

import jade.core.Agent;
import jade.domain.FIPANames;
import jade.lang.acl.ACLMessage;
import jade.proto.AchieveREResponder;
import smartfactory.dataStores.ProductionDataStore;

public class ProductionProvisioningResponderBehaviour extends AchieveREResponder {

	public ACLMessage getRequest() {
		return (ACLMessage) getDataStore().get(REQUEST_KEY);
	}

	public void setResponse(ACLMessage response) {
		getDataStore().put(RESPONSE_KEY, response);
	}

	public void setResult(ACLMessage result) {
		getDataStore().put(RESULT_NOTIFICATION_KEY, result);
	}

	public ProductionProvisioningResponderBehaviour(Agent a, ProductionDataStore dataStore) {
		super(a, AchieveREResponder.createMessageTemplate(FIPANames.InteractionProtocol.FIPA_REQUEST));

		registerHandleRequest(new DecisionBehaviour(this, dataStore));
		registerPrepareResultNotification(new ActivityBehaviour(this, dataStore));
	}

	private static final long serialVersionUID = -2388111063399359360L;
}
