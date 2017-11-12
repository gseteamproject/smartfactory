package smartfactory.behaviours.machine;

import org.jmock.Mockery;
import org.jmock.lib.legacy.ClassImposteriser;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import jade.core.Agent;
import smartfactory.dataStores.MachineDataStore;

public class ActivityResponderBehaviourTest {

	private final Mockery context = new Mockery() {
		{
			this.setImposteriser(ClassImposteriser.INSTANCE);
		}
	};

	Agent agent_mock;

	MachineDataStore dataStore_mock;

	ActivityResponderBehaviour testable;

	@Before
	public void setUp() {
		dataStore_mock = context.mock(MachineDataStore.class);
		agent_mock = context.mock(Agent.class);

		testable = new ActivityResponderBehaviour(agent_mock, dataStore_mock);
	}

	@After
	public void tearDown() {
		context.assertIsSatisfied();
	}

	@Test
	public void constructor() {
	}
}
