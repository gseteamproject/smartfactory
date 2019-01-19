package salesMarketBehaviours;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.lib.legacy.ClassImposteriser;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import interactors.OrderDataStore;
import interactors.RequestResult;
import interactors.ResponderBehaviour;
import jade.core.Agent;

public class SalesMarketAskBehaviourTest {

	private final Mockery context = new Mockery() {
		{
			this.setImposteriser(ClassImposteriser.INSTANCE);
		}
	};

	SalesMarketAskBehaviour testable;

	ResponderBehaviour responderBehaviour_mock;

	OrderDataStore dataStore_mock;

	Agent agent_mock;

	RequestResult requestResult_mock;

	@Before
	public void setUp() {
		responderBehaviour_mock = context.mock(ResponderBehaviour.class);
		dataStore_mock = context.mock(OrderDataStore.class);
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

		testable = new SalesMarketAskBehaviour(responderBehaviour_mock, dataStore_mock);
	}

	@After
	public void tearDown() {
		context.assertIsSatisfied();
	}

	@Test
	public void prepareRequests() {
	}
}
