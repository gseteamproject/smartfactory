package smartfactory.models;

import java.util.HashMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Production {

	public static final int DURATION_LIMIT = 5;

	private HashMap<String, Operation> operations;

	public Production() {
		operations = new HashMap<String, Operation>();
	}

	public String[] getOperations() {
		return operations.keySet().toArray(new String[0]);
	}

	public void addOperation(Operation operation) {
		operations.put(operation.name, operation);
	}

	public boolean willExecute(String operation) {
		logger.info("agreed to perform \"{}\"", operation);
		return true;
	}

	public boolean hasExecuted() {
		return true;
	}

	public void execute(String operationName) {
		Operation operation = operations.get(operationName);
		if (operation == null) {
			logger.error("not found \"{}\"...", operationName);
		} else {
			synchronized (this) {
				// blocking function call
				operation.execute();
			}
			logger.info("completed");
		}
	}

	public void getStatus() {
		logger.info("machine status: online");
	}

	public void terminate(String operation) {
		synchronized (this) {
			// blocking function call
		}
		logger.info("terminated \"{}\"", operation);
	}

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
}
