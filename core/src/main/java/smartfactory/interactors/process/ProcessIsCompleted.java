package smartfactory.interactors.process;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import smartfactory.interactors.Interactor;
import smartfactory.interactors.OneShotInteractor;
import smartfactory.utility.AgentDataStore;

public class ProcessIsCompleted extends Interactor implements OneShotInteractor {

	public ProcessIsCompleted(AgentDataStore dataObjects) {
		super(dataObjects);
	}

	@Override
	public void execute() {
		logger.info("product is in last state");
		// TODO : notify-all about process-completed-successfully
		agentDataStore.getEventSubsribers().notifyAll("process-completed");
	}

	@Override
	public int next() {
		return 0;
	}

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
}
