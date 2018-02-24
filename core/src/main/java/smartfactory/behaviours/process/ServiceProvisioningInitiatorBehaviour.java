package smartfactory.behaviours.process;

import smartfactory.behaviours.AchieveREInitiatorInteractorBehaviour;
import smartfactory.interactors.process.ServiceProvisioningInitiator;
import smartfactory.utility.AgentDataStore;

public class ServiceProvisioningInitiatorBehaviour extends AchieveREInitiatorInteractorBehaviour {

	public ServiceProvisioningInitiatorBehaviour(AgentDataStore dataStore) {
		super(new ServiceProvisioningInitiator(dataStore));
	}

	private static final long serialVersionUID = -6019599148725234374L;
}
