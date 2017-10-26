package smartfactory.models;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Machine {
	public String[] getOperations() {
		return new String[0];
	}

	public void execute(String operation) {
		logger.info("executing \"{}\"...", operation);
	}

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
}
