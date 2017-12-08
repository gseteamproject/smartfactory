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

	public boolean willExecute(String operationName) {
		logger.info("agreed to perform \"{}\"", operationName);
		return true;
	}

	public boolean hasExecuted() {
		return true;
	}

	public void execute(String operationName) {
		Operation operation = operations.get(operationName);
		if (operation == null) {
			logger.error("not found \"{}\"", operationName);
		} else {
			logger.info("executing \"{}\"", operationName);
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

	public void terminate(String operationName) {
		synchronized (this) {
			// blocking function call
		}
		logger.info("terminated \"{}\"", operationName);
	}

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
}
