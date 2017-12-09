package smartfactory.interactors.base;

import smartfactory.dataStores.BaseDataStore;

public class BaseInteractor {

	protected BaseDataStore dataStore;

	public BaseInteractor(BaseDataStore dataStore) {
		this.dataStore = dataStore;
	}
}
