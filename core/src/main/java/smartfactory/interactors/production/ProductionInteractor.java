package smartfactory.interactors.production;

import smartfactory.dataStores.ProductionDataStore;

public class ProductionInteractor {

	protected ProductionDataStore dataStore;

	public ProductionInteractor(ProductionDataStore dataStore) {
		this.dataStore = dataStore;
	}
}
