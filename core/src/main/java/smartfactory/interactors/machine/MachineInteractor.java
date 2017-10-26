package smartfactory.interactors.machine;

import smartfactory.dataStores.MachineDataStore;

public class MachineInteractor {

	protected MachineDataStore dataStore;

	public MachineInteractor(MachineDataStore dataStore) {
		this.dataStore = dataStore;
	}
}
