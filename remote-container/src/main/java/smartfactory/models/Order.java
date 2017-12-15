package smartfactory.models;

public class Order extends Product {

	@Override
	public String getRequiredServiceName() {
		return "block-production";
	}
}
