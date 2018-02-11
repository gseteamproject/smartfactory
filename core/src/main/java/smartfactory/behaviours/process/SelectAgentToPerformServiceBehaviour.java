package smartfactory.behaviours.process;

import smartfactory.behaviours.OneShotInteractorBehaviour;
import smartfactory.dataStores.ProcessDataStore;
import smartfactory.interactors.process.SelectAgentToPerformService;

public class SelectAgentToPerformServiceBehaviour extends OneShotInteractorBehaviour {

	public SelectAgentToPerformServiceBehaviour(ProcessDataStore dataStore) {
		super(new SelectAgentToPerformService(dataStore));
	}

	private static final long serialVersionUID = -4393537432332470688L;
}
