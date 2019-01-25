package procurementBehaviours;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.lib.legacy.ClassImposteriser;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import common.AgentDataStore;
import interactors.RequestResult;
import interactors.ResponderBehaviour;
import jade.core.Agent;

public class ProcurementAskBehaviourTest {

	private final Mockery context = new Mockery() {
		{
			this.setImposteriser(ClassImposteriser.INSTANCE);
		}
	};

	ProcurementAskBehaviour testable;

	ResponderBehaviour responderBehaviour_mock;

	AgentDataStore dataStore_mock;

	Agent agent_mock;

	RequestResult requestResult_mock;

	@Before
	public void setUp() {
		responderBehaviour_mock = context.mock(ResponderBehaviour.class);
		dataStore_mock = context.mock(AgentDataStore.class);
		agent_mock = context.mock(Agent.class);

		context.checking(new Expectations() {
			{
				oneOf(responderBehaviour_mock).getAgent();
				will(returnValue(agent_mock));

				oneOf(responderBehaviour_mock).getRequestResult();
				will(returnValue(requestResult_mock));
			}
		});

		testable = new ProcurementAskBehaviour(responderBehaviour_mock, dataStore_mock);
	}

	@After
	public void tearDown() {
		context.assertIsSatisfied();
	}

	@Test
	public void action() {
	}
}
