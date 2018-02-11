package smartfactory.agents;

import smartfactory.dataStores.ProductDataStore;
import smartfactory.models.Product;
import smartfactory.platform.JADEPlatform;

public class ProductAgent extends BaseAgent {

	private ProductDataStore dataStore;

	@Override
	protected void setupData() {
		dataStore = new ProductDataStore();
		dataStore.setProduct(createProduct());
		dataStore.setAgentPlatform(new JADEPlatform(this));
	}

	public Product createProduct() {
		return new Product();
	}

	private static final long serialVersionUID = 8653196782936628098L;
}
