package interactors;

import jade.core.Agent;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import jade.proto.AchieveREResponder;

public class ResponderBehaviour extends AchieveREResponder {

	public static RequestResult interactor;
	protected DeadlineBehaviour deadline;
	protected AskBehaviour askBehaviour;

	public ACLMessage getRequest() {
		return (ACLMessage) getDataStore().get(REQUEST_KEY);
	}

	public void setResponse(ACLMessage response) {
		getDataStore().put(RESPONSE_KEY, response);
	}

	public void setResult(ACLMessage result) {
		getDataStore().put(RESULT_NOTIFICATION_KEY, result);
	}

	public ResponderBehaviour(Agent a, MessageTemplate mt) {
		super(a, mt);
	}

	public void setup(OrderDataStore dataStore) {
		deadline = new DeadlineBehaviour(this, dataStore);
		dataStore.setDeadlineBehaviour(deadline);
		dataStore.setAskBehaviour(askBehaviour);
	}

	private static final long serialVersionUID = -6424797507265885988L;
}
