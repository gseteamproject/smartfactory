package smartfactory.behaviours.product;

import smartfactory.behaviours.OneShotInteractorBehaviour;
import smartfactory.dataStores.ProductDataStore;
import smartfactory.interactors.product.SelectAgentToPerformService;

public class SelectAgentToPerformServiceBehaviour extends OneShotInteractorBehaviour {

	public SelectAgentToPerformServiceBehaviour(ProductDataStore dataStore) {
		super(new SelectAgentToPerformService(dataStore));
	}

	private static final long serialVersionUID = -4393537432332470688L;
}
