package smartfactory.agents;

import jade.core.Agent;
import smartfactory.behaviours.FindStoreService;
import smartfactory.models.Block;

public class BlockAgent extends Agent {

	private Block block = new Block();

	@Override
	protected void setup() {
		addBehaviour(new FindStoreService(this));
	}

	@Override
	protected void takeDown() {
	}

	static public String getUniqueName() {
		return "block-" + Long.toString(System.currentTimeMillis());
	}

	private static final long serialVersionUID = 9181639673522822855L;
}
