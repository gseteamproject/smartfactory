package smartfactory.agents;

import smartfactory.models.Block;
import smartfactory.models.Product;

public class BlockAgent extends ProductAgent {

	static public String getUniqueName() {
		return "block-" + Long.toString(System.currentTimeMillis());
	}

	@Override
	public Product createProduct() {
		return new Block();
	}

	private static final long serialVersionUID = 9181639673522822855L;
}
