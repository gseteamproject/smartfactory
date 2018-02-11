package smartfactory.dataStores;

import smartfactory.models.Process;
import smartfactory.models.ProcessOperation;

public class ProcessDataStore extends BaseDataStore {

	private static final long serialVersionUID = 4572832486737254588L;

	public void setProcess(Process process) {
		put("process", process);
	}

	public Process getProcess() {
		return (Process) get("process");
	}

	public void setProcessOperation(ProcessOperation serviceProvisioning) {
		put("process-operation", serviceProvisioning);
	}

	public ProcessOperation getProcessOperation() {
		return (ProcessOperation) get("process-operation");
	}
}
