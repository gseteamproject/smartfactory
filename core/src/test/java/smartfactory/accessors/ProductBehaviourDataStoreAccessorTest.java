package smartfactory.accessors;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.lib.legacy.ClassImposteriser;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import jade.core.behaviours.DataStore;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import smartfactory.models.Product;

public class ProductBehaviourDataStoreAccessorTest {

	private final Mockery context = new Mockery() {
		{
			this.setImposteriser(ClassImposteriser.INSTANCE);
		}
	};

	DataStore dataStore_mock;

	ProductBehaviourDataStoreAccessor productBehaviourDataStoreAccessor;

	@Before
	public void setUp() {
		dataStore_mock = context.mock(DataStore.class);

		productBehaviourDataStoreAccessor = new ProductBehaviourDataStoreAccessor(dataStore_mock);
	}

	@After
	public void tearDown() {
		context.assertIsSatisfied();
	}
	
	@Test
	public void getProduct() {
		final Product product_mock = context.mock(Product.class);

		context.checking(new Expectations() {
			{
				oneOf(dataStore_mock).get("product");
				will(returnValue(product_mock));
			}
		});

		Assert.assertEquals(product_mock, productBehaviourDataStoreAccessor.getProduct());
	}

	@Test
	public void setRequiredService() {
		final ServiceDescription service_mock = context.mock(ServiceDescription.class);

		context.checking(new Expectations() {
			{
				oneOf(dataStore_mock).put("requiredService", service_mock);
			}
		});

		productBehaviourDataStoreAccessor.setRequiredService(service_mock);
	}
}
