package smartfactory.behaviours.product;

import smartfactory.behaviours.OneShotInteractorBehaviour;
import smartfactory.dataStores.ProductDataStore;
import smartfactory.interactors.product.ProductProcessIsIncorrect;

public class ProductProcessIsIncorrectBehaviour extends OneShotInteractorBehaviour {

	public ProductProcessIsIncorrectBehaviour(ProductDataStore dataStore) {
		super(new ProductProcessIsIncorrect(dataStore));
	}

	private static final long serialVersionUID = -3775938764499511209L;
}
