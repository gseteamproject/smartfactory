package smartfactory.models;

public class Product {

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

	public ServiceProvisioning createServiceProvisioning() {
		ServiceProvisioning serviceProvisioning = new ServiceProvisioning();
		serviceProvisioning.serviceName = getRequiredServiceName();
		return serviceProvisioning;
	}
}
