package procurementMarketBehaviours;

import org.jmock.Mockery;
import org.jmock.lib.legacy.ClassImposteriser;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import basicAgents.ProcurementMarketAgent;
import common.AgentDataStore;

public class ProcurementMarketResponderTest {

	private final Mockery context = new Mockery() {
		{
			this.setImposteriser(ClassImposteriser.INSTANCE);
		}
	};

	ProcurementMarketResponder testable;

	AgentDataStore dataStore_mock;

	ProcurementMarketAgent agent_mock;

	@Before
	public void setUp() {
		dataStore_mock = context.mock(AgentDataStore.class);
		agent_mock = context.mock(ProcurementMarketAgent.class);

		testable = new ProcurementMarketResponder(agent_mock, dataStore_mock);
	}

	@After
	public void tearDown() {
		context.assertIsSatisfied();
	}

	@Test
	public void constructor() {
		Assert.assertEquals(true, testable.getAskBehaviour() instanceof ProcurementMarketAskBehaviour);
		Assert.assertEquals(true, testable.getRequestResult() instanceof ProcurementMarketRequestResult);
	}
}
