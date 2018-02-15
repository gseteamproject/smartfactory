package smartfactory.interactors;

import smartfactory.utility.AgentDataStore;

public class Interactor {

	protected AgentDataStore agentDataStore;

	public Interactor(AgentDataStore dataStore) {
		this.agentDataStore = dataStore;
	}
}
