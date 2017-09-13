package smartfactory.agents;

import smartfactory.behaviours.FindStoreService;
import smartfactory.models.Block;

public class BlockAgent extends SmartFactoryAgent {

	private Block block = new Block();

	@Override
	protected void initializeBehaviours() {
		addBehaviour(new FindStoreService(this));
	}

	static public String getUniqueName() {
		return "block-" + Long.toString(System.currentTimeMillis());
	}

	private static final long serialVersionUID = 9181639673522822855L;
}
