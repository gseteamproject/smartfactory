package smartfactory.behaviours.order;

import smartfactory.behaviours.OneShotInteractorBehaviour;
import smartfactory.dataStores.OrderDataStore;
import smartfactory.interactors.order.SelectAgentToPerformProduction;

public class SelectAgentToPerformProductionBehaviour extends OneShotInteractorBehaviour {

	public SelectAgentToPerformProductionBehaviour(OrderDataStore orderDataStore) {
		super(new SelectAgentToPerformProduction(orderDataStore));
	}

	private static final long serialVersionUID = -3727053117223873631L;
}
