package smartfactory.agents;

import smartfactory.models.Product;

public class ProductAgent extends BaseAgent {

	@Override
	protected void setupData() {
		super.setupData();
		agentDataStore.setProduct(createProduct());
	}

	public Product createProduct() {
		return new Product();
	}

	private static final long serialVersionUID = 8653196782936628098L;
}
