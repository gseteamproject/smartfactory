package smartfactory.dataStores;

import org.jmock.Mockery;
import org.jmock.lib.legacy.ClassImposteriser;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import smartfactory.dataStores.ProductDataStore;
import smartfactory.models.Order;
import smartfactory.models.Product;
import smartfactory.platform.AgentPlatform;

public class ProductDataStoreTest {

	private final Mockery context = new Mockery() {
		{
			this.setImposteriser(ClassImposteriser.INSTANCE);
		}
	};

	ProductDataStore productDataStore;

	@Before
	public void setUp() {
		productDataStore = new ProductDataStore();
	}

	@After
	public void tearDown() {
		context.assertIsSatisfied();
	}

	@Test
	public void getProduct() {
		final Product product_mock = context.mock(Product.class);

		productDataStore.put("product", product_mock);

		Assert.assertEquals(product_mock, productDataStore.getProduct());
	}

	@Test
	public void setProduct() {
		final Product product_mock = context.mock(Product.class);

		productDataStore.setProduct(product_mock);

		Assert.assertEquals(product_mock, productDataStore.get("product"));
	}

	@Test
	public void getOrder() {
		final Order order_mock = context.mock(Order.class);

		productDataStore.put("order", order_mock);

		Assert.assertEquals(order_mock, productDataStore.getOrder());
	}

	@Test
	public void setOrder() {
		final Order order_mock = context.mock(Order.class);

		productDataStore.setOrder(order_mock);

		Assert.assertEquals(order_mock, productDataStore.get("order"));
	}

	@Test
	public void getAgentPlatform() {
		final AgentPlatform agentPlatform_mock = context.mock(AgentPlatform.class);

		productDataStore.put("agentPlatform", agentPlatform_mock);

		Assert.assertEquals(agentPlatform_mock, productDataStore.getAgentPlatform());
	}

	@Test
	public void setAgentPlatform() {
		final AgentPlatform agentPlatform_mock = context.mock(AgentPlatform.class);

		productDataStore.setAgentPlatform(agentPlatform_mock);

		Assert.assertEquals(agentPlatform_mock, productDataStore.get("agentPlatform"));
	}
}
