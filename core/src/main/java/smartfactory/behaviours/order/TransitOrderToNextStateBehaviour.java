package smartfactory.behaviours.order;

import smartfactory.behaviours.OneShotInteractorBehaviour;
import smartfactory.dataStores.OrderDataStore;
import smartfactory.interactors.order.TransitOrderToNextState;

public class TransitOrderToNextStateBehaviour extends OneShotInteractorBehaviour {

	public TransitOrderToNextStateBehaviour(OrderDataStore orderDataStore) {
		super(new TransitOrderToNextState(orderDataStore));
	}

	private static final long serialVersionUID = 909703837251923193L;
}
