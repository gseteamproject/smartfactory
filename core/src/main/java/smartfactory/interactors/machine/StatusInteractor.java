package smartfactory.interactors.machine;

import smartfactory.dataStores.MachineDataStore;

public class StatusInteractor extends MachineInteractor {

	public StatusInteractor(MachineDataStore dataStore) {
		super(dataStore);
	}

	public void execute() {
		dataStore.getMachine().getStatus();
	}
}
