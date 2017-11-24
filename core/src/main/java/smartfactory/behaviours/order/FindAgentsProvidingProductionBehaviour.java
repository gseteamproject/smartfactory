package smartfactory.behaviours.order;

import smartfactory.behaviours.OneShotInteractorBehaviour;
import smartfactory.dataStores.OrderDataStore;
import smartfactory.interactors.order.FindAgentsProvidingProduction;

public class FindAgentsProvidingProductionBehaviour extends OneShotInteractorBehaviour {

	public FindAgentsProvidingProductionBehaviour(OrderDataStore orderDataStore) {
		super(new FindAgentsProvidingProduction(orderDataStore));
	}

	private static final long serialVersionUID = -7869770137706969043L;
}
