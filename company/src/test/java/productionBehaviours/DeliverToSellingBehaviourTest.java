package productionBehaviours;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.lib.legacy.ClassImposteriser;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import basicAgents.ProductionAgent;
import interactors.OrderDataStore;

public class DeliverToSellingBehaviourTest {

	private final Mockery context = new Mockery() {
		{
			this.setImposteriser(ClassImposteriser.INSTANCE);
		}
	};

	DeliverToSellingBehaviour testable;

	ProductionResponder responderBehaviour_mock;

	OrderDataStore dataStore_mock;

	ProductionAgent agent_mock;

	@Before
	public void setUp() {
		responderBehaviour_mock = context.mock(ProductionResponder.class);
		dataStore_mock = context.mock(OrderDataStore.class);
		agent_mock = context.mock(ProductionAgent.class);

		context.checking(new Expectations() {
			{
				oneOf(responderBehaviour_mock).getAgent();
				will(returnValue(agent_mock));

				oneOf(dataStore_mock).getThisAgent();
				will(returnValue(agent_mock));
			}
		});

		testable = new DeliverToSellingBehaviour(responderBehaviour_mock, dataStore_mock);
	}

	@After
	public void tearDown() {
		context.assertIsSatisfied();
	}

	@Test
	public void action() {
	}
}
