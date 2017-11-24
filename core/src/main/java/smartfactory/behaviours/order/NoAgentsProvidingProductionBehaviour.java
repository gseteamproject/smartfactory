package smartfactory.behaviours.order;

import smartfactory.behaviours.OneShotInteractorBehaviour;
import smartfactory.dataStores.OrderDataStore;
import smartfactory.interactors.order.NoAgentsProvidingProduction;

public class NoAgentsProvidingProductionBehaviour extends OneShotInteractorBehaviour {

	public NoAgentsProvidingProductionBehaviour(OrderDataStore orderDataStore) {
		super(new NoAgentsProvidingProduction(orderDataStore));
	}

	private static final long serialVersionUID = 6672012443748113254L;
}
