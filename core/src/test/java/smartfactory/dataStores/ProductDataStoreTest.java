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
	public void getServiceProvisioning() {
		final ServiceProvisioning serviceProvisioning_mock = context.mock(ServiceProvisioning.class);

		testable.put("service-provisioning", serviceProvisioning_mock);

		Assert.assertEquals(serviceProvisioning_mock, testable.getServiceProvisioning());
	}

	@Test
	public void setServiceProvisioning() {
		final ServiceProvisioning serviceProvisioning_mock = context.mock(ServiceProvisioning.class);

		testable.setServiceProvisioning(serviceProvisioning_mock);

		Assert.assertEquals(serviceProvisioning_mock, testable.get("service-provisioning"));
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
