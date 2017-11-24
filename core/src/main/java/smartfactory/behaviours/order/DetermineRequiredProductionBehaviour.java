package smartfactory.behaviours.order;

import smartfactory.behaviours.OneShotInteractorBehaviour;
import smartfactory.dataStores.OrderDataStore;
import smartfactory.interactors.order.DetermineRequiredProduction;

public class DetermineRequiredProductionBehaviour extends OneShotInteractorBehaviour {

	public DetermineRequiredProductionBehaviour(OrderDataStore orderDataStore) {
		super(new DetermineRequiredProduction(orderDataStore));
	}

	private static final long serialVersionUID = -2888792759532920439L;
}
