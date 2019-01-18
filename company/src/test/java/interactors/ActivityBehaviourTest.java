package interactors;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.lib.legacy.ClassImposteriser;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import jade.core.Agent;

public class ActivityBehaviourTest {

	private final Mockery context = new Mockery() {
		{
			this.setImposteriser(ClassImposteriser.INSTANCE);
		}
	};

	ActivityBehaviour testable;

	ResponderBehaviour responderBehaviour_mock;

	DeadlineBehaviour deadlingBehaviour_mock;

	AskBehaviour askBehaviour_mock;

	Agent agent_mock;

	@Before
	public void setUp() {
		responderBehaviour_mock = context.mock(ResponderBehaviour.class);
		deadlingBehaviour_mock = context.mock(DeadlineBehaviour.class);
		askBehaviour_mock = context.mock(AskBehaviour.class);
		agent_mock = context.mock(Agent.class);

		context.checking(new Expectations() {
			{
				oneOf(responderBehaviour_mock).getAgent();
				will(returnValue(agent_mock));

				oneOf(responderBehaviour_mock).getDeadlineBehaviour();
				will(returnValue(deadlingBehaviour_mock));

				oneOf(deadlingBehaviour_mock).setAgent(agent_mock);

				oneOf(deadlingBehaviour_mock).isRunnable();
				will(returnValue(false));

				oneOf(responderBehaviour_mock).getAskBehaviour();
				will(returnValue(askBehaviour_mock));

				oneOf(askBehaviour_mock).setAgent(agent_mock);

				oneOf(askBehaviour_mock).isRunnable();
				will(returnValue(false));
			}
		});

		testable = new ActivityBehaviour(responderBehaviour_mock);
	}

	@After
	public void tearDown() {
		context.assertIsSatisfied();
	}

	@Test
	public void constructor() {
	}
}
