package smartfactory.interactors.process;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import smartfactory.interactors.OneShotInteractor;
import smartfactory.models.Event;
import smartfactory.utility.AgentDataStore;

public class ProcessIsIncorrect extends OneShotInteractor {

	public ProcessIsIncorrect(AgentDataStore dataStore) {
		super(dataStore);
	}

	@Override
	public void execute() {
		logger.info("process is incorrect");
		agentDataStore.getEventSubsribers().notifyAll(Event.PROCESS_COMPLETED_FAILURE);
	}

	@Override
	public int next() {
		return 0;
	}

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
}
