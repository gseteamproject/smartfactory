package customerBehaviours;

import org.jmock.Mockery;
import org.jmock.lib.legacy.ClassImposteriser;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import common.AgentDataStore;
import jade.core.Agent;

public class OneOrderBehaviourTest {

	private final Mockery context = new Mockery() {
		{
			this.setImposteriser(ClassImposteriser.INSTANCE);
		}
	};

	OneOrderBehaviour testable;

	Agent agent_mock;

	AgentDataStore agentDataStore_mock;

	@Before
	public void setUp() {
		agent_mock = context.mock(Agent.class);
		agentDataStore_mock = context.mock(AgentDataStore.class);

		testable = new OneOrderBehaviour(agent_mock, agentDataStore_mock);
	}

	@After
	public void tearDown() {
		context.assertIsSatisfied();
	}

	@Test
	public void action() {
	}
}
