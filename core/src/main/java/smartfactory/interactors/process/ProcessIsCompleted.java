package smartfactory.interactors.process;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import smartfactory.interactors.Interactor;
import smartfactory.interactors.OneShotInteractor;
import smartfactory.models.Event;
import smartfactory.utility.AgentDataStore;

public class ProcessIsCompleted extends Interactor implements OneShotInteractor {

	public ProcessIsCompleted(AgentDataStore dataObjects) {
		super(dataObjects);
	}

	@Override
	public void execute() {
		logger.info("process completed successfully");
		agentDataStore.getEventSubsribers().notifyAll(Event.PROCESS_COMPLETED_SUCCESS);
	}

	@Override
	public int next() {
		return 0;
	}

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
}
