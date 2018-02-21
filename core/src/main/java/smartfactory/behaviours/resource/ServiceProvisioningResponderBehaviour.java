package smartfactory.behaviours.resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jade.domain.FIPANames;
import jade.lang.acl.ACLMessage;
import jade.proto.AchieveREResponder;
import smartfactory.utility.AgentDataStore;

public class ServiceProvisioningResponderBehaviour extends AchieveREResponder {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	public ACLMessage getRequest() {
		return (ACLMessage) getDataStore().get(REQUEST_KEY);
	}

	public void setResponse(ACLMessage response) {
		getDataStore().put(RESPONSE_KEY, response);
	}

	public void setResult(ACLMessage result) {
		getDataStore().put(RESULT_NOTIFICATION_KEY, result);
	}

	public ServiceProvisioningResponderBehaviour(AgentDataStore dataStore) {
		super(null, AchieveREResponder.createMessageTemplate(FIPANames.InteractionProtocol.FIPA_REQUEST));

		registerHandleRequest(new DecisionBehaviour(this, dataStore));
		registerPrepareResultNotification(new ActivityBehaviour(this, dataStore));
	}

	@Override
	public void reset() {
		super.reset();
		logger.debug("reset method called");
	}

	private static final long serialVersionUID = -5604378107490850297L;
}
