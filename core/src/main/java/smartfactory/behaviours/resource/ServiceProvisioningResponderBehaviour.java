package smartfactory.behaviours.resource;

import jade.domain.FIPANames;
import jade.lang.acl.ACLMessage;
import jade.proto.AchieveREResponder;
import smartfactory.dataStores.ResourceDataStore;

public class ServiceProvisioningResponderBehaviour extends AchieveREResponder {

	public ACLMessage getRequest() {
		return (ACLMessage) getDataStore().get(REQUEST_KEY);
	}

	public void setResponse(ACLMessage response) {
		getDataStore().put(RESPONSE_KEY, response);
	}

	public void setResult(ACLMessage result) {
		getDataStore().put(RESULT_NOTIFICATION_KEY, result);
	}

	public ServiceProvisioningResponderBehaviour(ResourceDataStore dataStore) {
		super(null, AchieveREResponder.createMessageTemplate(FIPANames.InteractionProtocol.FIPA_REQUEST));

		registerHandleRequest(new DecisionBehaviour(this, dataStore));
		registerPrepareResultNotification(new ActivityBehaviour(this, dataStore));
	}

	private static final long serialVersionUID = -5604378107490850297L;
}
