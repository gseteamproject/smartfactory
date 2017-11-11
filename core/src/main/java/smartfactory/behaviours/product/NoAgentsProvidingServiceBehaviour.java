package smartfactory.behaviours.product;

import smartfactory.behaviours.OneShotInteractorBehaviour;
import smartfactory.dataStores.ProductDataStore;
import smartfactory.interactors.product.NoAgentsProvidingService;

public class NoAgentsProvidingServiceBehaviour extends OneShotInteractorBehaviour {

	public NoAgentsProvidingServiceBehaviour(ProductDataStore dataStore) {
		super(new NoAgentsProvidingService(dataStore));
	}

	private static final long serialVersionUID = 5429114934521142842L;
}
