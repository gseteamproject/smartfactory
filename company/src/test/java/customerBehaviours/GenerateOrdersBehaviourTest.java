package customerBehaviours;

import org.jmock.Mockery;
import org.jmock.lib.legacy.ClassImposteriser;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import jade.core.Agent;

public class GenerateOrdersBehaviourTest {

	private final Mockery context = new Mockery() {
		{
			this.setImposteriser(ClassImposteriser.INSTANCE);
		}
	};

	GenerateOrdersBehaviour testable;

	Agent agent_mock;

	long period;

	@Before
	public void setUp() {
		agent_mock = context.mock(Agent.class);
		period = 1000;

		testable = new GenerateOrdersBehaviour(agent_mock, period);
	}

	@After
	public void tearDown() {
		context.assertIsSatisfied();
	}

	@Test
	public void action() {
	}
}
