package smartfactory.interactors.process;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import smartfactory.eventSubscription.ontology.Event;
import smartfactory.interactors.OneShotInteractor;
import smartfactory.utility.AgentDataStore;

public class NoAgentsProvidingService extends OneShotInteractor {

	public NoAgentsProvidingService(AgentDataStore dataStore) {
		super(dataStore);
	}

	@Override
	public void execute() {
		logger.info("no agents providing service found");
		Event event = new Event();
		event.setId(Event.PROCESS_COMPLETED_FAILURE);
		agentDataStore.getEventSubsribers().notifyAll(event);
	}

	@Override
	public int next() {
		return 0;
	}

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
}
