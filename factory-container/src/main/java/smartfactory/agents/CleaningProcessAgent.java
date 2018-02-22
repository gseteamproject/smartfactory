package smartfactory.agents;

import smartfactory.models.CleaningProcess;
import smartfactory.models.Process;

public class CleaningProcessAgent extends ProcessAgent {

	private static final long serialVersionUID = -2291485117387362637L;

	static public String getUniqueName() {
		return "cleaning-process" + Long.toString(System.currentTimeMillis());
	}

	@Override
	public Process createProcess() {
		return new CleaningProcess();
	}
}
