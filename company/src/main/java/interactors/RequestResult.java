package interactors;

import common.AgentDataStore;
import jade.lang.acl.ACLMessage;

public class RequestResult {

	protected AgentDataStore dataStore;

	public boolean isDone;

	public RequestResult(AgentDataStore dataStore) {
		this.dataStore = dataStore;
	}

	public ACLMessage execute(ACLMessage request) {
		return null;
	}

	public boolean done() {
		return isDone;
	}

	public void reset() {
		this.isDone = false;
	}
}