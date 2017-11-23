package smartfactory.dataStores;

import org.jmock.Mockery;
import org.jmock.lib.legacy.ClassImposteriser;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import smartfactory.models.Order;

public class OrderDataStoreTest {

	private final Mockery context = new Mockery() {
		{
			this.setImposteriser(ClassImposteriser.INSTANCE);
		}
	};

	OrderDataStore testable;

	@Before
	public void setUp() {
		testable = new OrderDataStore();
	}

	@After
	public void tearDown() {
		context.assertIsSatisfied();
	}

	@Test
	public void getOrder() {
		final Order order_mock = context.mock(Order.class);

		testable.put("order", order_mock);

		Assert.assertEquals(order_mock, testable.getOrder());
	}

	@Test
	public void setOrder() {
		final Order order_mock = context.mock(Order.class);

		testable.setOrder(order_mock);

		Assert.assertEquals(order_mock, testable.get("order"));
	}
}
