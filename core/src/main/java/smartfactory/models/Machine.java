package smartfactory.models;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Machine {
	public final static int DURATION_LIMIT = 5;

	public String[] getOperations() {
		return new String[0];
	}

	public void execute(String operation) {
		logger.info("executing \"{}\"...", operation);
		synchronized (this) {
			// blocking function call
		}
		logger.info("completed");
	}

	public boolean willExecute(String operation) {
		logger.info("agreed to perform \"{}\"", operation);
		return true;
	}

	public void getStatus() {
		logger.info("machine status: online");
	}

	public boolean hasExecuted() {
		return true;
	}

	public void terminate(String operation) {
		synchronized (this) {
			// blocking function call
		}
		logger.info("terminated \"{}\"", operation);
	}

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
}
