package smartfactory.models;

public class Product {

	public String getRequiredServiceName() {
		// TODO Auto-generated method stub
		return null;
	}

	public void moveToNextState() {
		// TODO Auto-generated method stub
	}

	public static final int IsInTheLastState = 0;
	public static final int IsNotInTheLastState = 1;

	public int isInTheLastState() {
		return IsInTheLastState;
	}

	public Order createOrder() {
		Order order = new Order();
		order.serviceName = getRequiredServiceName();
		return order;
	}
}
