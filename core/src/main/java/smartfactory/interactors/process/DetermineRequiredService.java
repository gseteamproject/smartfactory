package smartfactory.interactors.process;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import smartfactory.dataStores.ProcessDataStore;
import smartfactory.interactors.OneShotInteractor;
import smartfactory.models.ProcessOperation;

public class DetermineRequiredService extends ProcessInteractor implements OneShotInteractor {

	public DetermineRequiredService(ProcessDataStore dataStore) {
		super(dataStore);
	}

	@Override
	public void execute() {
		ProcessOperation processOperation = dataStore.getProcess().getProcessOperation();
		dataStore.setProcessOperation(processOperation);
		logger.info("required service \"{}\"", processOperation.serviceName);
	}

	@Override
	public int next() {
		return dataStore.getProcessOperation().isServiceDetermined();
	}

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
}
