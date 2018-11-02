package smartfactory.interactors.process;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import smartfactory.interactors.OneShotInteractor;
import smartfactory.utility.AgentDataStore;

public class TransitProcessToNextOperation extends OneShotInteractor {

	public TransitProcessToNextOperation(AgentDataStore dataStore) {
		super(dataStore);
	}

	@Override
	public void execute() {
		agentDataStore.getProcess().moveToNextOperation();
		logger.info("associated process moved to next state");
	}

	@Override
	public int next() {
		return agentDataStore.getProcess().isCompleted();
	}

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
}
