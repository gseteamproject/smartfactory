package smartfactory.behaviours.process;

import smartfactory.behaviours.OneShotInteractorBehaviour;
import smartfactory.interactors.process.SelectAgentToPerformService;
import smartfactory.utility.AgentDataStore;

public class SelectAgentToPerformServiceBehaviour extends OneShotInteractorBehaviour {

	public SelectAgentToPerformServiceBehaviour(AgentDataStore dataStore) {
		super(new SelectAgentToPerformService(dataStore));
	}

	private static final long serialVersionUID = -4393537432332470688L;
}
