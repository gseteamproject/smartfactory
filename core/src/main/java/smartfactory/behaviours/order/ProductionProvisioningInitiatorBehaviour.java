package smartfactory.behaviours.order;

import smartfactory.behaviours.AchieveREInitiatorInteractorBehaviour;
import smartfactory.dataStores.OrderDataStore;
import smartfactory.interactors.order.ProductionProvisioningInitiator;

public class ProductionProvisioningInitiatorBehaviour extends AchieveREInitiatorInteractorBehaviour {

	public ProductionProvisioningInitiatorBehaviour(OrderDataStore orderDataStore) {
		super(new ProductionProvisioningInitiator(orderDataStore));
	}

	private static final long serialVersionUID = -75511853531025051L;
}
