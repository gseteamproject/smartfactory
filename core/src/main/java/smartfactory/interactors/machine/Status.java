package smartfactory.interactors.machine;

import smartfactory.dataStores.MachineDataStore;

public class Status extends MachineInteractor {

	public Status(MachineDataStore dataStore) {
		super(dataStore);
	}

	public void execute() {
		dataStore.getMachine().getStatus();
	}
}
