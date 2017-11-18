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
		ServiceProvisioning order = dataStore.getProduct().createServiceProvisioning();
		dataStore.setOrder(order);
		logger.info("required service \"{}\"", order.serviceName);
	}

	@Override
	public int next() {
		return dataStore.getOrder().isServiceDetermined();
	}

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
}
