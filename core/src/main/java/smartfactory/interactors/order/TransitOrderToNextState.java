package smartfactory.interactors.order;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import smartfactory.dataStores.OrderDataStore;
import smartfactory.interactors.OneShotInteractor;

public class TransitOrderToNextState extends OrderInteractor implements OneShotInteractor{

	public TransitOrderToNextState(OrderDataStore dataStore) {
		super(dataStore);
	}

	@Override
	public void execute() {
		dataStore.getOrder().moveToNextState();
		logger.info("associated order moved to next state");
	}

	@Override
	public int next() {
		return dataStore.getOrder().isInTheLastState();
	}
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
}
