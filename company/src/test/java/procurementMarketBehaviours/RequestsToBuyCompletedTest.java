package procurementMarketBehaviours;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.lib.legacy.ClassImposteriser;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import interactors.RequestResult;
import interactors.ResponderBehaviour;
import jade.lang.acl.ACLMessage;

public class RequestsToBuyCompletedTest {

	private final Mockery context = new Mockery() {
		{
			this.setImposteriser(ClassImposteriser.INSTANCE);
		}
	};

	RequestsToBuyCompleted testable;

	ResponderBehaviour responderBehaviour_mock;

	@Before
	public void setUp() {
		responderBehaviour_mock = context.mock(ResponderBehaviour.class);

		testable = new RequestsToBuyCompleted(responderBehaviour_mock);
	}

	@After
	public void tearDown() {
		context.assertIsSatisfied();
	}

	@Test
	public void action() {
		final RequestResult requestResult_mock = context.mock(RequestResult.class);
		final ACLMessage request_mock = context.mock(ACLMessage.class);

		context.checking(new Expectations() {
			{
				oneOf(responderBehaviour_mock).getRequestResult();
				will(returnValue(requestResult_mock));

				oneOf(responderBehaviour_mock).getRequest();
				will(returnValue(request_mock));

				oneOf(requestResult_mock).execute(request_mock);
			}
		});

		testable.action();
	}
}
