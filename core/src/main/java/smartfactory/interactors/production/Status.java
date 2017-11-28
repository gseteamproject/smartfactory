package smartfactory.interactors.production;

import smartfactory.dataStores.ProductionDataStore;

public class Status extends ProductionInteractor {

	public Status(ProductionDataStore dataStore) {
		super(dataStore);
	}

	public void execute() {
		dataStore.getProduction().getStatus();
	}
}
