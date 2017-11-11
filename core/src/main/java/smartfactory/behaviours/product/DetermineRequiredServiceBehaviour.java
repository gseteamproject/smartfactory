package smartfactory.behaviours.product;

import smartfactory.behaviours.OneShotInteractorBehaviour;
import smartfactory.dataStores.ProductDataStore;
import smartfactory.interactors.product.DetermineRequiredService;

public class DetermineRequiredServiceBehaviour extends OneShotInteractorBehaviour {

	public DetermineRequiredServiceBehaviour(ProductDataStore dataStore) {
		super(new DetermineRequiredService(dataStore));
	}

	private static final long serialVersionUID = 6700501632983375016L;
}
