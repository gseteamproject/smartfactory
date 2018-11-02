package smartfactory.interactors.process;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import smartfactory.interactors.OneShotInteractor;
import smartfactory.models.ProcessOperation;
import smartfactory.utility.AgentDataStore;

public class DetermineRequiredService extends OneShotInteractor {

	public DetermineRequiredService(AgentDataStore agentDataStore) {
		super(agentDataStore);
	}

	@Override
	public void execute() {
		ProcessOperation processOperation = agentDataStore.getProcess().getProcessOperation();
		agentDataStore.setProcessOperation(processOperation);
		logger.info("required service \"{}\"", processOperation.serviceName);
	}

	@Override
	public int next() {
		return agentDataStore.getProcessOperation().isServiceDetermined();
	}

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
}
