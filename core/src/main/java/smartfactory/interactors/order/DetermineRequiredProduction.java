package smartfactory.interactors.order;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import smartfactory.dataStores.OrderDataStore;
import smartfactory.interactors.OneShotInteractor;
import smartfactory.models.ProductionProvisioning;

public class DetermineRequiredProduction extends OrderInteractor implements OneShotInteractor {

	public DetermineRequiredProduction(OrderDataStore dataStore) {
		super(dataStore);
	}

	@Override
	public void execute() {
		ProductionProvisioning productionProvisioning = dataStore.getOrder().createProductionProvisioning();
		dataStore.setProductionProvisioning(productionProvisioning);
		logger.info("required service \"{}\"", productionProvisioning.productionName);
	}

	@Override
	public int next() {
		return dataStore.getProductionProvisioning().isProductionDetermined();
	}

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
}
