package smartfactory.interactors.product;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import smartfactory.dataStores.ProductDataStore;
import smartfactory.interactors.OneShotInteractor;
import smartfactory.models.ServiceProvisioning;

public class DetermineRequiredService extends ProductInteractor implements OneShotInteractor {

	public DetermineRequiredService(ProductDataStore dataStore) {
		super(dataStore);
	}

	@Override
	public void execute() {
		ServiceProvisioning serviceProvisioning = dataStore.getProduct().createServiceProvisioning();
		dataStore.setServiceProvisioning(serviceProvisioning);
		logger.info("required service \"{}\"", serviceProvisioning.serviceName);
	}

	@Override
	public int next() {
		return dataStore.getServiceProvisioning().isServiceDetermined();
	}

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
}
