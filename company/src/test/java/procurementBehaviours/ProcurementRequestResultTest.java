package procurementBehaviours;

import org.jmock.Mockery;
import org.jmock.lib.legacy.ClassImposteriser;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import common.AgentDataStore;
import jade.core.Agent;

public class ProcurementRequestResultTest {

	private final Mockery context = new Mockery() {
		{
			this.setImposteriser(ClassImposteriser.INSTANCE);
		}
	};

	ProcurementRequestResult testable;

	AgentDataStore dataStore_mock;

	Agent agent_mock;

	@Before
	public void setUp() {
		dataStore_mock = context.mock(AgentDataStore.class);
		agent_mock = context.mock(Agent.class);

		testable = new ProcurementRequestResult(dataStore_mock);
	}

	@After
	public void tearDown() {
		context.assertIsSatisfied();
	}

	@Test
	public void execute() {
	}
}
