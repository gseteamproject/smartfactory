package smartfactory.behaviours.base;

import smartfactory.behaviours.OneShotInteractorBehaviour;
import smartfactory.interactors.base.LaunchAgent;
import smartfactory.utility.AgentDataStore;

public class LaunchAgentBehaviour extends OneShotInteractorBehaviour {

	public LaunchAgentBehaviour(AgentDataStore dataStore) {
		super(new LaunchAgent(dataStore));
	}

	private static final long serialVersionUID = -3738136280724031758L;
}
