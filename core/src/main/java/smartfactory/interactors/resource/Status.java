package smartfactory.interactors.resource;

import smartfactory.dataStores.ResourceDataStore;

public class Status extends ResourceInteractor {

	public Status(ResourceDataStore dataStore) {
		super(dataStore);
	}

	public void execute() {
		dataStore.getResource().getStatus();
	}
}
