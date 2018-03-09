package smartfactory.models;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Resource {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	public final static int DURATION_LIMIT_IN_MILIS = 60000;

	public Resource() {
		logger.info("created");
	}

	public void execute(String operationName) {
		logger.info("executing \"{}\"", operationName);
	}

	public boolean willExecute(String operationName) {
		logger.info("agreed to perform \"{}\"", operationName);
		return true;
	}

	public void status(String operationName) {
		logger.info("status \"{}\"", operationName);
	}

	public void terminate(String operationName) {
		logger.info("terminating \"{}\"", operationName);
	}
}
