package interactors;

import common.AgentDataStore;
import jade.core.Agent;
import jade.domain.FIPANames;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import jade.proto.AchieveREResponder;
import ontology.CompanyOntology;

public class ResponderBehaviour extends AchieveREResponder {

	private static final long serialVersionUID = -6424797507265885988L;

	protected RequestResult requestResult;

	protected DeadlineBehaviour deadlineBehaviour;

	protected AskBehaviour askBehaviour;

	public RequestResult getRequestResult() {
		return requestResult;
	}

	public AskBehaviour getAskBehaviour() {
		return askBehaviour;
	}

	public DeadlineBehaviour getDeadlineBehaviour() {
		return deadlineBehaviour;
	}

	public ACLMessage getRequest() {
		return (ACLMessage) getDataStore().get(REQUEST_KEY);
	}

	public void setResponse(ACLMessage response) {
		getDataStore().put(RESPONSE_KEY, response);
	}

	public void setResult(ACLMessage result) {
		getDataStore().put(RESULT_NOTIFICATION_KEY, result);
	}

	public ResponderBehaviour(Agent a, AgentDataStore dataStore) {
		super(a, MessageTemplate.and(
				AchieveREResponder.createMessageTemplate(FIPANames.InteractionProtocol.FIPA_REQUEST),
				MessageTemplate.MatchOntology(CompanyOntology.ONTOLOGY_NAME)));
		deadlineBehaviour = new DeadlineBehaviour(this, dataStore);
	}
}
