package smartfactory.behaviours.machine;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.lib.legacy.ClassImposteriser;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import jade.core.Agent;
import smartfactory.dataStores.MachineDataStore;

public class DeadlineBehaviourTest {

	private final Mockery context = new Mockery() {
		{
			this.setImposteriser(ClassImposteriser.INSTANCE);
		}
	};

	ActivityResponderBehaviour interactionBehaviour_mock;

	MachineDataStore dataStore_mock;

	Agent agent_mock;

	DeadlineBehaviour testable;

	@Before
	public void setUp() {
		interactionBehaviour_mock = context.mock(ActivityResponderBehaviour.class);
		dataStore_mock = context.mock(MachineDataStore.class);
		agent_mock = context.mock(Agent.class);

		context.checking(new Expectations() {
			{
				oneOf(interactionBehaviour_mock).getAgent();
				will(returnValue(agent_mock));
			}
		});

		testable = new DeadlineBehaviour(interactionBehaviour_mock, dataStore_mock);
	}

	@After
	public void tearDown() {
		context.assertIsSatisfied();
	}

	@Test
	public void constructor() {
	}
}
