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

	ProductDataStore productDataStore_mock;

	DetermineRequiredService testable;

	@Before
	public void setUp() {
		productDataStore_mock = context.mock(ProductDataStore.class);

		testable = new DetermineRequiredService(productDataStore_mock);
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
				oneOf(productDataStore_mock).getProduct();
				will(returnValue(product_mock));

				oneOf(product_mock).createServiceProvisioning();
				will(returnValue(order_mock));

				oneOf(productDataStore_mock).setOrder(order_mock);
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
				oneOf(productDataStore_mock).getOrder();
				will(returnValue(order_mock));

				oneOf(order_mock).isServiceDetermined();
				will(returnValue(isServiceDetermined));
			}
		});

		Assert.assertEquals(isServiceDetermined, testable.next());
	}
}
