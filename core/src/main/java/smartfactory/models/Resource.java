package smartfactory.models;

import java.util.HashMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import smartfactory.utility.EventSubscribers;

public class Resource {

	public final static int DURATION_LIMIT = 5;

	private HashMap<String, ResourceOperation> operations;

	private EventSubscribers eventSubscribers;

	public Resource() {
		operations = new HashMap<String, ResourceOperation>();
	}

	public String[] getOperations() {
		return operations.keySet().toArray(new String[0]);
	}

	public void addOperation(ResourceOperation operation) {
		operation.setResource(this);
		operations.put(operation.name, operation);
	}

	public void execute(String operationName) {
		ResourceOperation operation = operations.get(operationName);
		if (operation == null) {
			logger.error("not found \"{}\"", operationName);
		} else {
			logger.info("executing \"{}\"", operationName);
			synchronized (this) {
				// TODO : move this to separate method or replace work behaviour with
				// StartExecution / CheckExecution
				operation.prepare();
				// blocking function call
				operation.execute();
			}
			logger.info("completed");
		}
	}

	public boolean willExecute(String operationName) {
		logger.info("agreed to perform \"{}\"", operationName);
		return true;
	}

	public void getStatus() {
		logger.info("resource status: online");
	}

	@Deprecated
	public boolean hasExecuted(String operationName) {
		ResourceOperation operation = operations.get(operationName);
		if (operation == null) {
			logger.error("not found \"{}\"", operationName);
		} else {
			logger.info("checking operation status \"{}\"", operationName);
			synchronized (this) {
				// blocking function call
				return operation.hasExecuted();
			}
		}
		return true;
	}

	public void terminate(String operationName) {
		ResourceOperation operation = operations.get(operationName);
		if (operation == null) {
			logger.error("not found \"{}\"", operationName);
		} else {
			logger.info("terminating \"{}\"", operationName);
			synchronized (this) {
				// blocking function call
				operation.terminate();
			}
			logger.info("terminated \"{}\"", operationName);
		}
	}

	public void notifyAll(String event) {
		eventSubscribers.notifyAll(event);
	}
	
	public void setEventSubscribers(EventSubscribers eventSubscribers) {
		this.eventSubscribers = eventSubscribers;
	}

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
}
