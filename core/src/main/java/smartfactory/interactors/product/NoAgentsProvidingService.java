package smartfactory.interactors.product;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import smartfactory.dataStores.ProductDataStore;
import smartfactory.interactors.OneShotInteractor;

public class NoAgentsProvidingService extends ProductInteractor implements OneShotInteractor {

	public NoAgentsProvidingService(ProductDataStore dataStore) {
		super(dataStore);
	}

	@Override
	public void execute() {
		logger.info("no agents providing service found");
		// TODO : notify-all about process-completed-with-failure
	}

	@Override
	public int next() {
		return 0;
	}

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
}
