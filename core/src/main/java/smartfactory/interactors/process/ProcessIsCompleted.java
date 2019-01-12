package smartfactory.interactors.process;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import smartfactory.interactors.OneShotInteractor;
import smartfactory.models.Event;
import smartfactory.utility.AgentDataStore;

public class ProcessIsCompleted extends OneShotInteractor {

	public ProcessIsCompleted(AgentDataStore dataObjects) {
		super(dataObjects);
	}

	@Override
	public void execute() {
		logger.info("process completed successfully");
		Event event = new Event();
		event.setId(Event.PROCESS_COMPLETED_SUCCESS);
		agentDataStore.getEventSubsribers().notifyAll(event);
	}

	@Override
	public int next() {
		return 0;
	}

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
}
