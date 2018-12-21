package basicClasses;

import org.jmock.Mockery;
import org.jmock.lib.legacy.ClassImposteriser;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class OrderTest {

	private final Mockery context = new Mockery() {
		{
			this.setImposteriser(ClassImposteriser.INSTANCE);
		}
	};

	Order testable;

	@Before
	public void setUp() {
		testable = new Order();
	}

	@After
	public void tearDown() {
		context.assertIsSatisfied();
	}

	@Test
	public void test_1_case_1() {
		Assert.assertTrue(testable.equals(testable));
	}

	@Test
	public void test_1_case_2() {
		Assert.assertFalse(testable.equals(null));
	}

	@Test
	public void test_1_case_3() {
		Assert.assertFalse(testable.equals(new Object()));
	}

	@Test
	public void test_1_case_4() {
		final int order_id = 100;
		final Order order = new Order();

		testable.id = order_id;
		order.id = order_id + 1;

		Assert.assertFalse(testable.equals(order));
	}

	@Test
	public void test_1_case_5() {
		final int order_id = 100;
		final Order order = new Order();
		final OrderPart orderPart_1_1_mock = new OrderPart(null);
		final OrderPart orderPart_1_2_mock = new OrderPart(null);
		final OrderPart orderPart_2_1_mock = orderPart_1_1_mock;
		final OrderPart orderPart_2_2_mock = orderPart_1_2_mock;

		testable.id = order_id;
		testable.orderList.add(orderPart_1_1_mock);
		testable.orderList.add(orderPart_1_2_mock);
		order.id = order_id;
		order.orderList.add(orderPart_2_1_mock);
		order.orderList.add(orderPart_2_2_mock);

		Assert.assertTrue(testable.equals(order));
	}

	@Test
	public void test_1_case_6() {
		final int order_id = 100;
		final Order order = new Order();
		final OrderPart orderPart_1_1_mock = new OrderPart(null);
		final OrderPart orderPart_1_2_mock = new OrderPart(null);
		final OrderPart orderPart_2_1_mock = orderPart_1_1_mock;
		final OrderPart orderPart_2_2_mock = new OrderPart(null);

		testable.id = order_id;
		testable.orderList.add(orderPart_1_1_mock);
		testable.orderList.add(orderPart_1_2_mock);
		order.id = order_id;
		order.orderList.add(orderPart_2_1_mock);
		order.orderList.add(orderPart_2_2_mock);

		Assert.assertFalse(testable.equals(order));
	}

	@Test
	public void test_1_case_7() {
		final int order_id = 100;
		final Order order = new Order();
		final OrderPart orderPart_1_1_mock = new OrderPart(null);
		final OrderPart orderPart_1_2_mock = new OrderPart(null);
		final OrderPart orderPart_2_1_mock = orderPart_1_1_mock;

		testable.id = order_id;
		testable.orderList.add(orderPart_1_1_mock);
		testable.orderList.add(orderPart_1_2_mock);
		order.id = order_id;
		order.orderList.add(orderPart_2_1_mock);

		Assert.assertFalse(testable.equals(order));
	}
}
