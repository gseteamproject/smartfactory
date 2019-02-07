package interactors;

import org.jmock.Mockery;
import org.jmock.lib.legacy.ClassImposteriser;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import common.AgentDataStore;
import jade.lang.acl.ACLMessage;

public class RequestResultTest {

	private final Mockery context = new Mockery() {
		{
			this.setImposteriser(ClassImposteriser.INSTANCE);
		}
	};

	RequestResult testable;

	AgentDataStore orderDataStore_mock;

	@Before
	public void setUp() {
		orderDataStore_mock = context.mock(AgentDataStore.class);

		testable = new RequestResult(orderDataStore_mock);
	}

	@After
	public void tearDown() {
		context.assertIsSatisfied();
	}

	@Test
	public void execute() {
		final ACLMessage message_mock = context.mock(ACLMessage.class);

		Assert.assertEquals(null, testable.execute(message_mock));
	}

	@Test
	public void done() {
		Assert.assertEquals(false, testable.done());
	}

	@Test
	public void reset() {
		testable.isDone = true;

		testable.reset();
		Assert.assertEquals(false, testable.isDone);
	}
}
