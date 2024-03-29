package smartfactory.serviceProvisioning.behaviours;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.lib.legacy.ClassImposteriser;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import jade.core.Agent;
import smartfactory.utility.AgentDataStore;

public class ExecutionDeadlineBehaviourTest {

	private final Mockery context = new Mockery() {
		{
			this.setImposteriser(ClassImposteriser.INSTANCE);
		}
	};

	ServiceProvisioningResponderBehaviour interactionBehaviour_mock;

	AgentDataStore dataStore_mock;

	Agent agent_mock;

	ExecutionDeadlineBehaviour testable;

	@Before
	public void setUp() {
		interactionBehaviour_mock = context.mock(ServiceProvisioningResponderBehaviour.class);
		dataStore_mock = context.mock(AgentDataStore.class);
		agent_mock = context.mock(Agent.class);

		context.checking(new Expectations() {
			{
				oneOf(interactionBehaviour_mock).getAgent();
				will(returnValue(agent_mock));
			}
		});

		testable = new ExecutionDeadlineBehaviour(interactionBehaviour_mock, dataStore_mock);
	}

	@After
	public void tearDown() {
		context.assertIsSatisfied();
	}

	@Test
	public void constructor() {
	}
}
