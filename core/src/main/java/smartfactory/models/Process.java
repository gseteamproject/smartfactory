package smartfactory.models;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Process {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	protected List<ProcessOperation> operations;

	private int currentOperationIdx;

	public Process() {
		operations = new ArrayList<ProcessOperation>();
		currentOperationIdx = 0;
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
