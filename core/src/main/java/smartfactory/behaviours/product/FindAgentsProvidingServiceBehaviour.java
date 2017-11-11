package smartfactory.behaviours.product;

import smartfactory.behaviours.OneShotInteractorBehaviour;
import smartfactory.dataStores.ProductDataStore;
import smartfactory.interactors.product.FindAgentsProvidingService;

public class FindAgentsProvidingServiceBehaviour extends OneShotInteractorBehaviour {

	public FindAgentsProvidingServiceBehaviour(ProductDataStore dataStore) {
		super(new FindAgentsProvidingService(dataStore));
	}

	private static final long serialVersionUID = -3668701638612933859L;
}
