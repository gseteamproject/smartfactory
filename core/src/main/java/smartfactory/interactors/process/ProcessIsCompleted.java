package smartfactory.interactors.process;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import smartfactory.dataStores.ProcessDataStore;
import smartfactory.interactors.OneShotInteractor;

public class ProcessIsCompleted extends ProcessInteractor implements OneShotInteractor {

	public ProcessIsCompleted(ProcessDataStore dataStore) {
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
