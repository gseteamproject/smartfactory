package smartfactory.behaviours.machine;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.lib.legacy.ClassImposteriser;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import jade.core.Agent;
import smartfactory.dataStores.MachineDataStore;

public class StatusBehaviourTest {

	private final Mockery context = new Mockery() {
		{
			this.setImposteriser(ClassImposteriser.INSTANCE);
		}
	};

	ServiceProvisioningResponderBehaviour interactionBehaviour_mock;

	MachineDataStore dataStore_mock;

	Agent agent_mock;

	StatusBehaviour testable;

	@Before
	public void setUp() {
		interactionBehaviour_mock = context.mock(ServiceProvisioningResponderBehaviour.class);
		dataStore_mock = context.mock(MachineDataStore.class);
		agent_mock = context.mock(Agent.class);

		context.checking(new Expectations() {
			{
				oneOf(interactionBehaviour_mock).getAgent();
				will(returnValue(agent_mock));
			}
		});

		testable = new StatusBehaviour(interactionBehaviour_mock, dataStore_mock);
	}

	@After
	public void tearDown() {
		context.assertIsSatisfied();
	}

	@Test
	public void constructor() {
	}
}
