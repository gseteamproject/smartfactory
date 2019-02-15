package productionBehaviours;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.lib.legacy.ClassImposteriser;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import common.AgentDataStore;
import jade.core.Agent;
import jade.lang.acl.ACLMessage;

public class ProductionRequestResultTest {

	private final Mockery context = new Mockery() {
		{
			this.setImposteriser(ClassImposteriser.INSTANCE);
		}
	};

	ProductionRequestResult testable;

	AgentDataStore dataStore_mock;

	Agent agent_mock;

	@Before
	public void setUp() {
		dataStore_mock = context.mock(AgentDataStore.class);
		agent_mock = context.mock(Agent.class);

		testable = new ProductionRequestResult(dataStore_mock);
	}

	@After
	public void tearDown() {
		context.assertIsSatisfied();
	}

	@Test
	public void execute_is_produced() {
		final ACLMessage request_mock = context.mock(ACLMessage.class, "request");
		final ACLMessage response_mock = context.mock(ACLMessage.class, "response");
		final String content = "{\"id\":0,\"orderList\":[],\"deadline\":" + (System.currentTimeMillis() + 100)
				+ ",\"price\":0}";

		context.checking(new Expectations() {
			{
				oneOf(request_mock).createReply();
				will(returnValue(response_mock));

				oneOf(request_mock).getContent();
				will(returnValue(content));

				oneOf(response_mock).setContent(content);

				oneOf(dataStore_mock).getDeadlineResult();
				will(returnValue(false));

				oneOf(dataStore_mock).getIsProduced();
				will(returnValue(true));

				oneOf(response_mock).setPerformative(ACLMessage.INFORM);
			}
		});

		Assert.assertEquals(response_mock, testable.execute(request_mock));
		Assert.assertEquals(true, testable.isDone);
	}

	@Test
	public void execute_is_not_produced() {
		final ACLMessage request_mock = context.mock(ACLMessage.class, "request");
		final ACLMessage response_mock = context.mock(ACLMessage.class, "response");
		final String content = "{\"id\":0,\"orderList\":[],\"deadline\":" + (System.currentTimeMillis() + 100)
				+ ",\"price\":0}";

		context.checking(new Expectations() {
			{
				oneOf(request_mock).createReply();
				will(returnValue(response_mock));

				oneOf(request_mock).getContent();
				will(returnValue(content));

				oneOf(response_mock).setContent(content);

				oneOf(dataStore_mock).getDeadlineResult();
				will(returnValue(false));

				oneOf(dataStore_mock).getIsProduced();
				will(returnValue(false));

				oneOf(response_mock).setPerformative(ACLMessage.FAILURE);
			}
		});

		Assert.assertEquals(response_mock, testable.execute(request_mock));
		Assert.assertEquals(false, testable.isDone);
	}

	@Test
	public void execute_deadline() {
		final ACLMessage request_mock = context.mock(ACLMessage.class, "request");
		final ACLMessage response_mock = context.mock(ACLMessage.class, "response");
		final String content = "{\"id\":0,\"orderList\":[],\"deadline\":" + (System.currentTimeMillis() + 100)
				+ ",\"price\":0}";

		context.checking(new Expectations() {
			{
				oneOf(request_mock).createReply();
				will(returnValue(response_mock));

				oneOf(request_mock).getContent();
				will(returnValue(content));

				oneOf(response_mock).setContent(content);

				oneOf(dataStore_mock).getDeadlineResult();
				will(returnValue(true));

				oneOf(response_mock).setPerformative(ACLMessage.FAILURE);
			}
		});

		Assert.assertEquals(response_mock, testable.execute(request_mock));
		Assert.assertEquals(false, testable.isDone);
	}
}
