package smartfactory.agents;

import smartfactory.behaviours.ProductBehaviour;
import smartfactory.models.Product;

public class ProductAgent extends SmartFactoryAgent {

	private Product product;

	@Override
	protected void initializeData() {
		product = createProduct();
	}

	public Product createProduct() {
		return new Product();
	}

	@Override
	protected void initializeBehaviours() {
		addBehaviour(new ProductBehaviour(this, product));
	}

	private static final long serialVersionUID = 8653196782936628098L;
}
