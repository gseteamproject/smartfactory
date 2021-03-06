package smartfactory.serviceProvisioning.behaviours;

import jade.domain.FIPANames;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import jade.proto.AchieveREResponder;
import smartfactory.serviceProvisioning.ontology.ServiceProvisioningOntology;
import smartfactory.utility.AgentDataStore;

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

	public ServiceProvisioningResponderBehaviour(AgentDataStore dataStore) {
		super(null,
				MessageTemplate.and(
						AchieveREResponder.createMessageTemplate(FIPANames.InteractionProtocol.FIPA_REQUEST),
						MessageTemplate.MatchOntology(ServiceProvisioningOntology.ONTOLOGY_NAME)));

		registerHandleRequest(new DecisionBehaviour(this, dataStore));
		registerPrepareResultNotification(new ExecutionBehaviour(this, dataStore));
	}

	private static final long serialVersionUID = -5604378107490850297L;
}
