package smartfactory.interactors.product;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.lib.legacy.ClassImposteriser;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import smartfactory.dataStores.ProductDataStore;
import smartfactory.models.Order;
import smartfactory.models.Product;

public class DetermineRequiredServiceTest {

	private final Mockery context = new Mockery() {
		{
			this.setImposteriser(ClassImposteriser.INSTANCE);
		}
	};

	ProductDataStore productDataStore_mock;

	DetermineRequiredService determineRequiredService;

	@Before
	public void setUp() {
		productDataStore_mock = context.mock(ProductDataStore.class);

		determineRequiredService = new DetermineRequiredService(productDataStore_mock);
	}

	@After
	public void tearDown() {
		context.assertIsSatisfied();
	}

	@Test
	public void action() {
		final Product product_mock = context.mock(Product.class);
		final Order order_mock = context.mock(Order.class);

		context.checking(new Expectations() {
			{
				oneOf(productDataStore_mock).getProduct();
				will(returnValue(product_mock));

				oneOf(product_mock).createOrder();
				will(returnValue(order_mock));

				oneOf(productDataStore_mock).setOrder(order_mock);
			}
		});

		determineRequiredService.execute();
	}

	@Test
	public void onEnd() {
		final Order order_mock = context.mock(Order.class);
		final int isServiceDetermined = Order.ServiceDetermined;

		context.checking(new Expectations() {
			{
				oneOf(productDataStore_mock).getOrder();
				will(returnValue(order_mock));

				oneOf(order_mock).isServiceDetermined();
				will(returnValue(isServiceDetermined));
			}
		});

		Assert.assertEquals(isServiceDetermined, determineRequiredService.next());
	}
}
