package smartfactory.interactors.order;

import smartfactory.dataStores.OrderDataStore;

public class OrderInteractor {

	protected OrderDataStore dataStore;

	public OrderInteractor(OrderDataStore dataStore) {
		this.dataStore = dataStore;
	}
}
