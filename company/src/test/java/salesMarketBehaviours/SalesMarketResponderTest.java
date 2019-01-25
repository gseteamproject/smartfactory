package salesMarketBehaviours;

import org.jmock.Mockery;
import org.jmock.lib.legacy.ClassImposteriser;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import basicAgents.SalesMarketAgent;
import common.AgentDataStore;

public class SalesMarketResponderTest {

	private final Mockery context = new Mockery() {
		{
			this.setImposteriser(ClassImposteriser.INSTANCE);
		}
	};

	SalesMarketResponder testable;

	AgentDataStore dataStore_mock;

	SalesMarketAgent agent_mock;

	@Before
	public void setUp() {
		dataStore_mock = context.mock(AgentDataStore.class);
		agent_mock = context.mock(SalesMarketAgent.class);

		testable = new SalesMarketResponder(agent_mock, dataStore_mock);
	}

	@After
	public void tearDown() {
		context.assertIsSatisfied();
	}

	@Test
	public void constructor() {
	}
}
