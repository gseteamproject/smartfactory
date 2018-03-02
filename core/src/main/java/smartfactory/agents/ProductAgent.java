package smartfactory.agents;

import smartfactory.models.Product;

public class ProductAgent extends BaseAgent {

	private static final long serialVersionUID = 8653196782936628098L;

	public Product createProduct() {
		return new Product();
	}

	@Override
	protected void setupData() {
		super.setupData();
		agentDataStore.setProduct(createProduct());
	}
}
