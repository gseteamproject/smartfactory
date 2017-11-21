package smartfactory.agents;

import smartfactory.behaviours.product.ProductBehaviour;
import smartfactory.dataStores.ProductDataStore;
import smartfactory.models.Product;
import smartfactory.models.ServiceProvisioning;
import smartfactory.platform.JADEPlatform;

public class ProductAgent extends SmartFactoryAgent {

	private ProductDataStore dataStore;

	@Override
	protected void initializeData() {
		dataStore = new ProductDataStore();
		dataStore.setProduct(createProduct());
		dataStore.setServiceProvisioning(new ServiceProvisioning());
		dataStore.setAgentPlatform(new JADEPlatform(this));
	}

	public Product createProduct() {
		return new Product();
	}

	@Override
	protected void initializeBehaviours() {
		addBehaviour(new ProductBehaviour(this, dataStore));
	}

	private static final long serialVersionUID = 8653196782936628098L;
}
