package smartfactory.models;

public class OrderProcess extends Process {

	@Override
	public String getRequiredServiceName() {
		return "block-production";
	}
}
