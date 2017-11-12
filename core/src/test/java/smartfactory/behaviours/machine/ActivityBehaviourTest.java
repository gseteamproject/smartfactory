package smartfactory.behaviours.machine;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.lib.legacy.ClassImposteriser;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import jade.core.Agent;
import smartfactory.dataStores.MachineDataStore;

public class ActivityBehaviourTest {

	private final Mockery context = new Mockery() {
		{
			this.setImposteriser(ClassImposteriser.INSTANCE);
		}
	};

	ActivityResponderBehaviour interactionBehaviour_mock;

	MachineDataStore dataStore_mock;

	Agent agent_mock;

	ActivityBehaviour testable;

	@Before
	public void setUp() {
		interactionBehaviour_mock = context.mock(ActivityResponderBehaviour.class);
		dataStore_mock = context.mock(MachineDataStore.class);
		agent_mock = context.mock(Agent.class);

		context.checking(new Expectations() {
			{
				exactly(3).of(interactionBehaviour_mock).getAgent();
				will(returnValue(agent_mock));
			}
		});

		testable = new ActivityBehaviour(interactionBehaviour_mock, dataStore_mock);
	}

	@After
	public void tearDown() {
		context.assertIsSatisfied();
	}

	@Test
	public void constructor() {
	}
}
