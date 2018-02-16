package smartfactory.models;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ResourceOperation {

	public String name;

	public ResourceOperation(String name) {
		this.name = name;
	}

	public void execute() {
		// TODO : add method for overriding without output
		logger.info("...", name);

		// TODO : send
		// agentDataStore.getEventSubsribers().notifyAll("operation-completed");
	}

	public void terminate() {
		logger.info("...", name);

		// TODO : send
		// agentDataStore.getEventSubsribers().notifyAll("operation-completed");

	}

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
}
