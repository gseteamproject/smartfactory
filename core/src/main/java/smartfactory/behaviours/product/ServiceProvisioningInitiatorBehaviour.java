package smartfactory.behaviours.product;

import smartfactory.behaviours.AchieveREInitiatorInteractorBehaviour;
import smartfactory.dataStores.ProductDataStore;
import smartfactory.interactors.product.ServiceProvisioningInitiator;

public class ServiceProvisioningInitiatorBehaviour extends AchieveREInitiatorInteractorBehaviour {

	public ServiceProvisioningInitiatorBehaviour(ProductDataStore dataStore) {
		super(new ServiceProvisioningInitiator(dataStore));
	}

	private static final long serialVersionUID = -6019599148725234374L;
}
