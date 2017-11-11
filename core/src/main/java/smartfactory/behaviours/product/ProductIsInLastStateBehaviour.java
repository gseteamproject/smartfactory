package smartfactory.behaviours.product;

import smartfactory.behaviours.OneShotInteractorBehaviour;
import smartfactory.dataStores.ProductDataStore;
import smartfactory.interactors.product.ProductIsInLastState;

public class ProductIsInLastStateBehaviour extends OneShotInteractorBehaviour {

	public ProductIsInLastStateBehaviour(ProductDataStore dataStore) {
		super(new ProductIsInLastState(dataStore));
	}

	private static final long serialVersionUID = 8566459465592445582L;
}
