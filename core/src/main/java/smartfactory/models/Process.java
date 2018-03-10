package smartfactory.models;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import smartfactory.configuration.ProcessConfiguration;
import smartfactory.configuration.ProcessOperationConfiguration;

public class Process {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	protected List<ProcessOperation> operations = new ArrayList<ProcessOperation>();

	private int currentOperationIdx;

	public Process() {
		currentOperationIdx = 0;
	}

	public Process(ProcessConfiguration processConfiguration) {
		this();
		for (ProcessOperationConfiguration operationConfiguration : processConfiguration
				.getOperationsConfigurations()) {
			operations.add(new ProcessOperation(operationConfiguration.getName()));
		}
	}

	public void moveToNextOperation() {
		currentOperationIdx++;
	}

	public static final int IsCompleted = 0;

	public static final int IsNotCompleted = 1;

	public int isCompleted() {
		if (currentOperationIdx < operations.size()) {
			return IsNotCompleted;
		}
		return IsCompleted;
	}

	public ProcessOperation getProcessOperation() {
		try {
			return operations.get(currentOperationIdx);
		} catch (IndexOutOfBoundsException e) {
			logger.error("", e);
			return new ProcessOperation();
		}
	}
}
