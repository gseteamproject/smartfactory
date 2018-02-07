package smartfactory.interactors.product;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import smartfactory.dataStores.ProductDataStore;
import smartfactory.interactors.OneShotInteractor;

public class ProductIsInLastState extends ProductInteractor implements OneShotInteractor {

	public ProductIsInLastState(ProductDataStore dataStore) {
		super(dataStore);
	}

	@Override
	public void execute() {
		logger.info("product is in last state");
		// TODO : notify-all about process-completed-successfully
	}

	@Override
	public int next() {
		return 0;
	}

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
}
