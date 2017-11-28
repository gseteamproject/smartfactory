package smartfactory.behaviours.customer;

import smartfactory.behaviours.OneShotInteractorBehaviour;
import smartfactory.dataStores.CustomerDataStore;
import smartfactory.interactors.customer.LaunchAgent;

public class LaunchAgentBehaviour extends OneShotInteractorBehaviour {
	
	public LaunchAgentBehaviour(CustomerDataStore dataStore) {
		super(new LaunchAgent(dataStore));
	}

	private static final long serialVersionUID = -3738136280724031758L;
}
