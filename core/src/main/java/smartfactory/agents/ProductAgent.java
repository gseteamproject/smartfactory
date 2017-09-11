package smartfactory.agents;

import smartfactory.behaviours.ProductMainBehaviour;

public class ProductAgent extends SmartFactoryAgent {

	@Override
	protected void initializeBehaviours() {
		addBehaviour(new ProductMainBehaviour(this));
	}

	private static final long serialVersionUID = 8653196782936628098L;
}
