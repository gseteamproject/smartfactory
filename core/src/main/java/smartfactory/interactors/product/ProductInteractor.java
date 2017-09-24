package smartfactory.interactors.product;

import smartfactory.dataStores.ProductDataStore;

public class ProductInteractor {

	protected ProductDataStore dataStore;

	public ProductInteractor(ProductDataStore dataStore) {
		this.dataStore = dataStore;
	}
}
