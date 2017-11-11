package smartfactory.behaviours.product;

import smartfactory.behaviours.OneShotInteractorBehaviour;
import smartfactory.dataStores.ProductDataStore;
import smartfactory.interactors.product.TransitProductToNextState;

public class TransitProductToNextStateBehaviour extends OneShotInteractorBehaviour {

	public TransitProductToNextStateBehaviour(ProductDataStore dataStore) {
		super(new TransitProductToNextState(dataStore));
	}

	private static final long serialVersionUID = 8235523498019703146L;
}
