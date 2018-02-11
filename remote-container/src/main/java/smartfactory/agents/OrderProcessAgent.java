package smartfactory.agents;

import smartfactory.models.OrderProcess;
import smartfactory.models.Process;

public class OrderProcessAgent extends ProcessAgent {

	public static String getUniqueName() {
		return "order-" + Long.toString(System.currentTimeMillis());
	}

	@Override
	public Process createProcess() {
		return new OrderProcess();
	}

	private static final long serialVersionUID = -5529204273917293075L;
}
