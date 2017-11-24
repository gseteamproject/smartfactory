package smartfactory.behaviours.order;

import smartfactory.behaviours.OneShotInteractorBehaviour;
import smartfactory.dataStores.OrderDataStore;
import smartfactory.interactors.order.OrderIsInLastState;

public class OrderIsInLastStateBehaviour extends OneShotInteractorBehaviour {

	public OrderIsInLastStateBehaviour(OrderDataStore orderDataStore) {
		super(new OrderIsInLastState(orderDataStore));
	}

	private static final long serialVersionUID = -3961100130269752216L;
}
