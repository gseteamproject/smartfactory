package interactors;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.lib.legacy.ClassImposteriser;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import common.AgentDataStore;
import jade.core.Agent;

public class AskBehaviourTest {

	private final Mockery context = new Mockery() {
		{
			this.setImposteriser(ClassImposteriser.INSTANCE);
		}
	};

	AskBehaviour testable;

	ResponderBehaviour responderBehaviour_mock;

	AgentDataStore orderDataStore_mock;

	Agent agent_mock;

	RequestResult requestResult_mock;

	@Before
	public void setUp() {
		responderBehaviour_mock = context.mock(ResponderBehaviour.class);
		orderDataStore_mock = context.mock(AgentDataStore.class);
		agent_mock = context.mock(Agent.class);
		requestResult_mock = context.mock(RequestResult.class);

		context.checking(new Expectations() {
			{
				oneOf(responderBehaviour_mock).getAgent();
				will(returnValue(agent_mock));

				oneOf(responderBehaviour_mock).getRequestResult();
				will(returnValue(requestResult_mock));
			}
		});

		testable = new AskBehaviour(responderBehaviour_mock, orderDataStore_mock);
	}

	@After
	public void tearDown() {
		context.assertIsSatisfied();
	}

	@Test
	public void constructor() {
	}
}
