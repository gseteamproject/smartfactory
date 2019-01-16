package interactors;

import org.jmock.Mockery;
import org.jmock.lib.legacy.ClassImposteriser;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import jade.core.Agent;

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
	public void getThisAgent() {
		final Agent mock = context.mock(Agent.class, "agent_mock");

		testable.put("thisAgent", mock);

		Assert.assertEquals(mock, testable.getThisAgent());
	}

	@Test
	public void setThisAgent() {
		final Agent mock = context.mock(Agent.class, "agent_mock");

		testable.setThisAgent(mock);

		Assert.assertEquals(mock, testable.get("thisAgent"));
	}
}
