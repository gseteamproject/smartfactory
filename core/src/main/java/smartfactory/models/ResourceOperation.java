package smartfactory.models;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ResourceOperation {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	public String name;

	public boolean executed;

	private Resource resource;

	public ResourceOperation(String name) {
		this.name = name;
	}

	public void prepare() {
		executed = false;
	}

	public void execute() {
		logger.info("...");

		executed = true;
		completedSuccess();
	}

	public void terminate() {
		logger.info("...", name);

		executed = true;
		completedFailure();
	}

	@Deprecated
	public boolean hasExecuted() {
		return executed;
	}

	public void setResource(Resource resource) {
		this.resource = resource;
	}

	protected void completedSuccess() {
		resource.notifyAll(Event.OPERATION_COMPLETED_SUCCESS);
	}

	protected void completedFailure() {
		resource.notifyAll(Event.OPERATION_COMPLETED_FAILURE);
	}
}
