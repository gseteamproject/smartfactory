package productionBehaviours;

import org.jmock.Mockery;
import org.jmock.lib.legacy.ClassImposteriser;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import common.AgentDataStore;
import jade.core.Agent;

public class ProductionResponderTest {

	private final Mockery context = new Mockery() {
		{
			this.setImposteriser(ClassImposteriser.INSTANCE);
		}
	};

	ProductionResponder testable;

	AgentDataStore dataStore_mock;

	Agent agent_mock;

	@Before
	public void setUp() {
		dataStore_mock = context.mock(AgentDataStore.class);
		agent_mock = context.mock(Agent.class);

		testable = new ProductionResponder(agent_mock, dataStore_mock);
	}

	@After
	public void tearDown() {
		context.assertIsSatisfied();
	}

	@Test
	public void constructor() {
		Assert.assertEquals(true, testable.getAskBehaviour() instanceof ProductionAskBehaviour);
		Assert.assertEquals(true, testable.getRequestResult() instanceof ProductionRequestResult);
	}
}
