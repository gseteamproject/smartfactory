package smartfactory.interactors.order;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import smartfactory.dataStores.OrderDataStore;
import smartfactory.interactors.OneShotInteractor;

public class OrderProcessIsIncorrect extends OrderInteractor implements OneShotInteractor {

	public OrderProcessIsIncorrect(OrderDataStore dataStore) {
		super(dataStore);
	}

	@Override
	public void execute() {
		logger.info("order process is incorrect");
	}

	@Override
	public int next() {
		return 0;
	}

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
}
