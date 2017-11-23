package smartfactory.behaviours.product;

import smartfactory.behaviours.AchieveREInitiatorInteractorBehaviour;
import smartfactory.dataStores.ProductDataStore;
import smartfactory.interactors.product.PerformServiceInitiator;

public class ServiceProvisioningInitiatorBehaviour extends AchieveREInitiatorInteractorBehaviour {

	public ServiceProvisioningInitiatorBehaviour(ProductDataStore dataStore) {
		super(new PerformServiceInitiator(dataStore));
	}

	private static final long serialVersionUID = -6019599148725234374L;
}
