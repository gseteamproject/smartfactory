package smartfactory.interactors.order;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import smartfactory.dataStores.OrderDataStore;
import smartfactory.interactors.OneShotInteractor;

public class OrderIsInLastState extends OrderInteractor implements OneShotInteractor {

	public OrderIsInLastState(OrderDataStore dataStore) {
		super(dataStore);
	}

	@Override
	public void execute() {
		logger.info("order is in last state");
	}

	@Override
	public int next() {
		return 0;
	}

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
}
