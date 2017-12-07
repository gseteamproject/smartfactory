package smartfactory.models;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Operation {

	public String name;

	public Operation(String name) {
		this.name = name;
	}

	public void execute() {
		logger.info("executing \"{}\"...", name);
	}

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
}
