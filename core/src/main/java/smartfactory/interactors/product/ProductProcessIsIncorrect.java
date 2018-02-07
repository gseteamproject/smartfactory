package smartfactory.interactors.product;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import smartfactory.dataStores.ProductDataStore;
import smartfactory.interactors.OneShotInteractor;

public class ProductProcessIsIncorrect extends ProductInteractor implements OneShotInteractor {

	public ProductProcessIsIncorrect(ProductDataStore dataStore) {
		super(dataStore);
	}

	@Override
	public void execute() {
		logger.info("product process is incorrect");
		// TODO : notify-all about process-completed-with-failure
	}

	@Override
	public int next() {
		return 0;
	}

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
}
