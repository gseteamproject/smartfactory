package smartfactory.behaviours.process;

import smartfactory.behaviours.AchieveREInitiatorInteractorBehaviour;
import smartfactory.dataStores.ProcessDataStore;
import smartfactory.interactors.process.ServiceProvisioningInitiator;

public class ServiceProvisioningInitiatorBehaviour extends AchieveREInitiatorInteractorBehaviour {

	public ServiceProvisioningInitiatorBehaviour(ProcessDataStore dataStore) {
		super(new ServiceProvisioningInitiator(dataStore));
	}

	private static final long serialVersionUID = -6019599148725234374L;
}
