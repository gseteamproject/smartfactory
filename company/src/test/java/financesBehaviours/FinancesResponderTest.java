package financesBehaviours;

import org.jmock.Mockery;
import org.jmock.lib.legacy.ClassImposteriser;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import common.AgentDataStore;
import jade.core.Agent;

public class FinancesResponderTest {

	private final Mockery context = new Mockery() {
		{
			this.setImposteriser(ClassImposteriser.INSTANCE);
		}
	};

	FinancesResponder testable;

	AgentDataStore agentDataStore_mock;

	Agent agent_mock;

	@Before
	public void setUp() {
		agentDataStore_mock = context.mock(AgentDataStore.class);
		agent_mock = context.mock(Agent.class);

		testable = new FinancesResponder(agent_mock, agentDataStore_mock);
	}

	@After
	public void tearDown() {
		context.assertIsSatisfied();
	}

	@Test
	public void constructor() {
		Assert.assertEquals(true, testable.getAskBehaviour() instanceof FinancesAskBehaviour);
		Assert.assertEquals(true, testable.getRequestResult() instanceof FinancesRequestResult);
	}
}
