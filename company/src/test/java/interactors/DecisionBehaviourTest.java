package interactors;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.lib.legacy.ClassImposteriser;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import jade.lang.acl.ACLMessage;

public class DecisionBehaviourTest {

	private final Mockery context = new Mockery() {
		{
			this.setImposteriser(ClassImposteriser.INSTANCE);
		}
	};

	DecisionBehaviour testable;

	ResponderBehaviour responderBehaviour_mock;

	Decision decision_mock;

	@Before
	public void setUp() {
		responderBehaviour_mock = context.mock(ResponderBehaviour.class);
		decision_mock = context.mock(Decision.class);

		testable = new DecisionBehaviour(responderBehaviour_mock, decision_mock);
	}

	@After
	public void tearDown() {
		context.assertIsSatisfied();
	}

	@Test
	public void action() {
		final ACLMessage request_mock = context.mock(ACLMessage.class, "request");
		final ACLMessage response_mock = context.mock(ACLMessage.class, "response");

		context.checking(new Expectations() {
			{
				oneOf(responderBehaviour_mock).getRequest();
				will(returnValue(request_mock));

				oneOf(decision_mock).execute(request_mock);
				will(returnValue(response_mock));

				oneOf(responderBehaviour_mock).setResponse(response_mock);
			}
		});

		testable.action();
	}
}
