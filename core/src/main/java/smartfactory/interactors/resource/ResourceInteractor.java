package smartfactory.interactors.resource;

import smartfactory.dataStores.ResourceDataStore;

public class ResourceInteractor {

	protected ResourceDataStore dataStore;

	public ResourceInteractor(ResourceDataStore dataStore) {
		this.dataStore = dataStore;
	}
}
