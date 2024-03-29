package common;

import java.util.ArrayList;
import java.util.List;

import org.jmock.Mockery;
import org.jmock.lib.legacy.ClassImposteriser;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import basicClasses.Order;

public class AgentDataStoreTest {

	private final Mockery context = new Mockery() {
		{
			this.setImposteriser(ClassImposteriser.INSTANCE);
		}
	};

	AgentDataStore testable;

	@Before
	public void setUp() {
		testable = new AgentDataStore();
	}

	@After
	public void tearDown() {
		context.assertIsSatisfied();
	}

	@Test
	public void setIsInMaterialStorage() {
		final boolean value = true;

		testable.setIsInMaterialStorage(value);

		Assert.assertEquals(value, testable.get("isInMaterialStorage"));
	}

	@Test
	public void getIsInMaterialStorage() {
		final boolean value = true;

		testable.put("isInMaterialStorage", value);

		Assert.assertEquals(value, testable.getIsInMaterialStorage());
	}

	@Test
	public void setIsGiven() {
		final boolean value = true;

		testable.setIsGiven(value);

		Assert.assertEquals(value, testable.get("isGiven"));
	}

	@Test
	public void getIsGiven() {
		final boolean value = true;

		testable.put("isGiven", value);

		Assert.assertEquals(value, testable.getIsGiven());
	}

	@Test
	public void setIsProduced() {
		final boolean value = true;

		testable.setIsProduced(value);

		Assert.assertEquals(value, testable.get("isProduced"));
	}

	@Test
	public void getIsProduced() {
		final boolean value = true;

		testable.put("isProduced", value);

		Assert.assertEquals(value, testable.getIsProduced());
	}

	@Test
	public void setIsTaken() {
		final boolean value = true;

		testable.setIsTaken(value);

		Assert.assertEquals(value, testable.get("isTaken"));
	}

	@Test
	public void getIsTaken() {
		final boolean value = true;

		testable.put("isTaken", value);

		Assert.assertEquals(value, testable.getIsTaken());
	}

	@Test
	public void setIsInWarehouse() {
		final boolean value = true;

		testable.setIsInWarehouse(value);

		Assert.assertEquals(value, testable.get("isInWarehouse"));
	}

	@Test
	public void getIsInWarehouse() {
		final boolean value = true;

		testable.put("isInWarehouse", value);

		Assert.assertEquals(value, testable.getIsInWarehouse());
	}

	@Test
	public void setProductionQueue() {
		final List<Order> queue = new ArrayList<Order>();

		testable.setProductionQueue(queue);

		Assert.assertEquals(queue, testable.get("productionQueue"));
	}

	@Test
	public void getProductionQueue() {
		final List<Order> queue = new ArrayList<Order>();

		testable.put("productionQueue", queue);

		Assert.assertEquals(queue, testable.getProductionQueue());
	}

	@Test
	public void setGoodName() {
		final String value = "string";

		testable.setGoodName(value);

		Assert.assertEquals(value, testable.get("goodName"));
	}

	@Test
	public void getGoodName() {
		final String value = "string";

		testable.put("goodName", value);

		Assert.assertEquals(value, testable.getGoodName());
	}

	@Test
	public void setAgentPlatform() {
		final AgentPlatform agentPlatform_mock = context.mock(AgentPlatform.class);

		testable.setAgentPlatform(agentPlatform_mock);

		Assert.assertEquals(agentPlatform_mock, testable.get("agentPlatform"));
	}

	@Test
	public void getAgentPlatform() {
		final AgentPlatform agentPlatform_mock = context.mock(AgentPlatform.class);

		testable.put("agentPlatform", agentPlatform_mock);

		Assert.assertEquals(agentPlatform_mock, testable.getAgentPlatform());
	}

	@Test
	public void setOrder() {
		final Order order_mock = context.mock(Order.class);

		testable.setOrder(order_mock);

		Assert.assertEquals(order_mock, testable.get("order"));
	}

	@Test
	public void getOrder() {
		final Order order_mock = context.mock(Order.class);

		testable.put("order", order_mock);

		Assert.assertEquals(order_mock, testable.getOrder());
	}
}
