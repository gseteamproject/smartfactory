package smartfactory.behaviours.order;

import smartfactory.behaviours.OneShotInteractorBehaviour;
import smartfactory.dataStores.OrderDataStore;
import smartfactory.interactors.order.OrderProcessIsIncorrect;

public class OrderProcessIsIncorrectBehaviour extends OneShotInteractorBehaviour {

	public OrderProcessIsIncorrectBehaviour(OrderDataStore orderDataStore) {
		super(new OrderProcessIsIncorrect(orderDataStore));
	}

	private static final long serialVersionUID = 757029890298301937L;
}
