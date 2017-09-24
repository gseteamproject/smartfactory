package smartfactory.interactors.product;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import smartfactory.dataStores.ProductDataStore;
import smartfactory.interactors.OneShotInteractor;

public class TransitProductToNextState extends ProductInteractor implements OneShotInteractor {

	public TransitProductToNextState(ProductDataStore dataStore) {
		super(dataStore);
	}

	@Override
	public void execute() {
		dataStore.getProduct().moveToNextState();
		logger.info("associated product moved to next state");
	}

	@Override
	public int next() {
		return dataStore.getProduct().isInTheLastState();
	}

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
}
