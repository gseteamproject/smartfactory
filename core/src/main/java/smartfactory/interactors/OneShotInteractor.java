package smartfactory.interactors;

import smartfactory.utility.AgentDataStore;

public abstract class OneShotInteractor extends Interactor {

	public OneShotInteractor(AgentDataStore agentDataStore) {
		super(agentDataStore);
	}

	public abstract void execute();

	public abstract int next();
}
