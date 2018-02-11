package smartfactory.models;

public class Process {

	public String getRequiredServiceName() {
		return null;
	}

	public void moveToNextState() {
	}

	public static final int IsInTheLastState = 0;
	public static final int IsNotInTheLastState = 1;

	public int isInTheLastState() {
		return IsInTheLastState;
	}

	public ProcessOperation createProcessOperation() {
		ProcessOperation serviceProvisioning = new ProcessOperation();
		serviceProvisioning.serviceName = getRequiredServiceName();
		return serviceProvisioning;
	}
}
