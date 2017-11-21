package smartfactory.agents;

public class OrderAgent extends SmartFactoryAgent {

	private static final long serialVersionUID = -5529204273917293075L;

	public static String getUniqueName() {
		return "order-" + Long.toString(System.currentTimeMillis());
	}
}
