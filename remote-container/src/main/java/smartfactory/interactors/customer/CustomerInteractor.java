package smartfactory.interactors.customer;

import smartfactory.dataStores.CustomerDataStore;

public class CustomerInteractor {

	protected CustomerDataStore dataStore;

	public CustomerInteractor(CustomerDataStore dataStore) {
		this.dataStore = dataStore;
	}
}
