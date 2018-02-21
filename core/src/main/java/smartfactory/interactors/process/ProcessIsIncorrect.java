package smartfactory.interactors.process;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import smartfactory.interactors.Interactor;
import smartfactory.interactors.OneShotInteractor;
import smartfactory.utility.AgentDataStore;

public class ProcessIsIncorrect extends Interactor implements OneShotInteractor {

	public ProcessIsIncorrect(AgentDataStore dataStore) {
		super(dataStore);
	}

	@Override
	public void execute() {
		logger.info("product process is incorrect");
		// TODO : notify-all about process-completed-with-failure
		agentDataStore.getEventSubsribers().notifyAll("process-completed");
	}

	@Override
	public int next() {
		return 0;
	}

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
}
