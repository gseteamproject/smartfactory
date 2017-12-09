package smartfactory.behaviours.base;

import smartfactory.behaviours.OneShotInteractorBehaviour;
import smartfactory.dataStores.BaseDataStore;
import smartfactory.interactors.base.LaunchAgent;

public class LaunchAgentBehaviour extends OneShotInteractorBehaviour {

	public LaunchAgentBehaviour(BaseDataStore dataStore) {
		super(new LaunchAgent(dataStore));
	}

	private static final long serialVersionUID = -3738136280724031758L;
}
