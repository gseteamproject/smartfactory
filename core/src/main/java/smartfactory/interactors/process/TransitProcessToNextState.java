package smartfactory.interactors.process;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import smartfactory.dataStores.ProcessDataStore;
import smartfactory.interactors.OneShotInteractor;

public class TransitProcessToNextState extends ProcessInteractor implements OneShotInteractor {

	public TransitProcessToNextState(ProcessDataStore dataStore) {
		super(dataStore);
	}

	@Override
	public void execute() {
		dataStore.getProcess().moveToNextState();
		logger.info("associated process moved to next state");
	}

	@Override
	public int next() {
		return dataStore.getProcess().isInTheLastState();
	}

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
}
