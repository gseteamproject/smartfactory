package smartfactory.models;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Machine {

	public final static int DURATION_LIMIT = 5;

	// TODO : replace List with HashMap
	protected List<Operation> operations;

	public Machine() {
		operations = new ArrayList<Operation>();
	}

	public String[] getOperations() {
		String[] operationNames = new String[operations.size()];
		for (int i = 0; i < operations.size(); i++) {
			operationNames[i] = operations.get(i).name;
		}
		return operationNames;
	}

	public void execute(String operationName) {
		// TODO : replace with lookup
		Operation operation = null;
		for (int i = 0; i < operations.size(); i++) {
			operation = operations.get(i);
			if (StringUtils.compare(operationName, operation.name) == 0) {
				break;
			}
			operation = null;
		}
		if (operation == null) {
			logger.error("not found \"{}\"...", operationName);
		}
		synchronized (this) {
			// blocking function call
			operation.execute();
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
