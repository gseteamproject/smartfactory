package smartfactory.agents;

import jade.core.Agent;

public class BlockAgent extends Agent {

	static public String getUniqueName() {
		return "block-" + Long.toString(System.currentTimeMillis());
	}

	private static final long serialVersionUID = 9181639673522822855L;
}
