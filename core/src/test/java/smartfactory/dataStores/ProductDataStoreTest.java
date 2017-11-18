package smartfactory.dataStores;

import org.jmock.Mockery;
import org.jmock.lib.legacy.ClassImposteriser;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import smartfactory.dataStores.ProductDataStore;
import smartfactory.models.ServiceProvisioning;
import smartfactory.models.Product;
import smartfactory.platform.AgentPlatform;

public class ProductDataStoreTest {

	private final Mockery context = new Mockery() {
		{
			this.setImposteriser(ClassImposteriser.INSTANCE);
		}
	};

	ProductDataStore testable;

	@Before
	public void setUp() {
		testable = new ProductDataStore();
	}

	@After
	public void tearDown() {
		context.assertIsSatisfied();
	}

	@Test
	public void getProduct() {
		final Product product_mock = context.mock(Product.class);

		testable.put("product", product_mock);

		Assert.assertEquals(product_mock, testable.getProduct());
	}

	@Test
	public void setProduct() {
		final Product product_mock = context.mock(Product.class);

		testable.setProduct(product_mock);

		Assert.assertEquals(product_mock, testable.get("product"));
	}

	@Test
	public void getOrder() {
		final ServiceProvisioning order_mock = context.mock(ServiceProvisioning.class);

		testable.put("order", order_mock);

		Assert.assertEquals(order_mock, testable.getOrder());
	}

	@Test
	public void setOrder() {
		final ServiceProvisioning order_mock = context.mock(ServiceProvisioning.class);

		testable.setOrder(order_mock);

		Assert.assertEquals(order_mock, testable.get("order"));
	}

	@Test
	public void getAgentPlatform() {
		final AgentPlatform agentPlatform_mock = context.mock(AgentPlatform.class);

		testable.put("agentPlatform", agentPlatform_mock);

		Assert.assertEquals(agentPlatform_mock, testable.getAgentPlatform());
	}

	@Test
	public void setAgentPlatform() {
		final AgentPlatform agentPlatform_mock = context.mock(AgentPlatform.class);

		testable.setAgentPlatform(agentPlatform_mock);

		Assert.assertEquals(agentPlatform_mock, testable.get("agentPlatform"));
	}
}
