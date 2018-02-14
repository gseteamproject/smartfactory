package smartfactory.interactors.process;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import smartfactory.dataStores.ProcessDataStore;
import smartfactory.interactors.OneShotInteractor;

public class TransitProcessToNextOperation extends ProcessInteractor implements OneShotInteractor {

	public TransitProcessToNextOperation(ProcessDataStore dataStore) {
		super(dataStore);
	}

	@Override
	public void execute() {
		dataStore.getProcess().moveToNextOperation();
		logger.info("associated process moved to next state");
	}

	@Override
	public int next() {
		return dataStore.getProcess().isCompleted();
	}

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
}
