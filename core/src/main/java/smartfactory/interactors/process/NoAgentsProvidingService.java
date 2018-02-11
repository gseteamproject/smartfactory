package smartfactory.interactors.process;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import smartfactory.dataStores.ProcessDataStore;
import smartfactory.interactors.OneShotInteractor;

public class NoAgentsProvidingService extends ProcessInteractor implements OneShotInteractor {

	public NoAgentsProvidingService(ProcessDataStore dataStore) {
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
