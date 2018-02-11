package smartfactory.behaviours.process;

import smartfactory.behaviours.OneShotInteractorBehaviour;
import smartfactory.dataStores.ProcessDataStore;
import smartfactory.interactors.process.ProductIsInLastState;

public class ProductIsInLastStateBehaviour extends OneShotInteractorBehaviour {

	public ProductIsInLastStateBehaviour(ProcessDataStore dataStore) {
		super(new ProductIsInLastState(dataStore));
	}

	private static final long serialVersionUID = 8566459465592445582L;
}
