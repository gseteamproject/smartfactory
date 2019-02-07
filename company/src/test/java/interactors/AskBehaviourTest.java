package interactors;

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

public class AskBehaviourTest {

	private final Mockery context = new Mockery() {
		{
			this.setImposteriser(ClassImposteriser.INSTANCE);
		}
	};

	AskBehaviour testable;

	ResponderBehaviour responderBehaviour_mock;

	AgentDataStore agentDataStore_mock;

	RequestResult requestResult_mock;

	@Before
	public void setUp() {
		responderBehaviour_mock = context.mock(ResponderBehaviour.class);
		agentDataStore_mock = context.mock(AgentDataStore.class);
		requestResult_mock = context.mock(RequestResult.class);

		context.checking(new Expectations() {
			{
				oneOf(responderBehaviour_mock).getRequestResult();
				will(returnValue(requestResult_mock));
			}
		});

		testable = new AskBehaviour(responderBehaviour_mock, agentDataStore_mock);
	}

	@After
	public void tearDown() {
		context.assertIsSatisfied();
	}

	@Test
	public void action() {
		testable.action();
	}

	@Test
	public void isStarted() {
		Assert.assertEquals(false, testable.isStarted());
	}

	@Test
	public void setStarted() {
		testable.setStarted(true);

		Assert.assertEquals(true, testable.isStarted());
	}

	@Test
	public void reset() {
		testable.reset();

		Assert.assertEquals(false, testable.isStarted());
	}

	@Test
	public void done() {
		final Agent agent_mock = context.mock(Agent.class);
		final ACLMessage requestMessage_mock = context.mock(ACLMessage.class, "request");
		final ACLMessage responseMessage_mock = context.mock(ACLMessage.class, "response");

		context.checking(new Expectations() {
			{
				oneOf(requestResult_mock).done();
				will(returnValue(true));

				oneOf(responderBehaviour_mock).getAgent();
				will(returnValue(agent_mock));

				oneOf(responderBehaviour_mock).getRequest();
				will(returnValue(requestMessage_mock));

				oneOf(requestResult_mock).execute(requestMessage_mock);
				will(returnValue(responseMessage_mock));

				oneOf(responderBehaviour_mock).setResult(responseMessage_mock);
			}
		});

		Assert.assertEquals(true, testable.done());
	}

	@Test
	public void done_false() {
		context.checking(new Expectations() {
			{
				oneOf(requestResult_mock).done();
				will(returnValue(false));
			}
		});

		Assert.assertEquals(false, testable.done());
	}
}
