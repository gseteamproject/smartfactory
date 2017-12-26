package smartfactory.interactors.product;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.lib.legacy.ClassImposteriser;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import smartfactory.dataStores.ProductDataStore;
import smartfactory.models.ServiceProvisioning;
import smartfactory.models.Product;

public class DetermineRequiredServiceTest {

	private final Mockery context = new Mockery() {
		{
			this.setImposteriser(ClassImposteriser.INSTANCE);
		}
	};

	ProductDataStore dataStore_mock;

	DetermineRequiredService testable;

	@Before
	public void setUp() {
		dataStore_mock = context.mock(ProductDataStore.class);

		testable = new DetermineRequiredService(dataStore_mock);
	}

	@After
	public void tearDown() {
		context.assertIsSatisfied();
	}

	@Test
	public void execute() {
		final Product product_mock = context.mock(Product.class);
		final ServiceProvisioning order_mock = context.mock(ServiceProvisioning.class);

		context.checking(new Expectations() {
			{
				oneOf(dataStore_mock).getProduct();
				will(returnValue(product_mock));

				oneOf(product_mock).createServiceProvisioning();
				will(returnValue(order_mock));

				oneOf(dataStore_mock).setServiceProvisioning(order_mock);
			}
		});

		testable.execute();
	}

	@Test
	public void next() {
		final ServiceProvisioning order_mock = context.mock(ServiceProvisioning.class);
		final int isServiceDetermined = ServiceProvisioning.ServiceDetermined;

		context.checking(new Expectations() {
			{
				oneOf(dataStore_mock).getServiceProvisioning();
				will(returnValue(order_mock));

				oneOf(order_mock).isServiceDetermined();
				will(returnValue(isServiceDetermined));
			}
		});

		Assert.assertEquals(isServiceDetermined, testable.next());
	}
}
