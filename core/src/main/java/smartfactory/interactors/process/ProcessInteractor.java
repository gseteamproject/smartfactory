package smartfactory.interactors.process;

import smartfactory.dataStores.ProcessDataStore;

public class ProcessInteractor {

	protected ProcessDataStore dataStore;

	public ProcessInteractor(ProcessDataStore dataStore) {
		this.dataStore = dataStore;
	}
}
