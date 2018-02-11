package smartfactory.behaviours.resource;

import org.jmock.Mockery;
import org.jmock.lib.legacy.ClassImposteriser;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import smartfactory.behaviours.resource.DecisionBehaviour;
import smartfactory.behaviours.resource.ServiceProvisioningResponderBehaviour;
import smartfactory.dataStores.ResourceDataStore;

public class DecisionBehaviourTest {

	private final Mockery context = new Mockery() {
		{
			this.setImposteriser(ClassImposteriser.INSTANCE);
		}
	};

	ServiceProvisioningResponderBehaviour interactionBehaviour_mock;

	ResourceDataStore dataStore_mock;

	DecisionBehaviour testable;

	@Before
	public void setUp() {
		interactionBehaviour_mock = context.mock(ServiceProvisioningResponderBehaviour.class);
		dataStore_mock = context.mock(ResourceDataStore.class);

		testable = new DecisionBehaviour(interactionBehaviour_mock, dataStore_mock);
	}

	@After
	public void tearDown() {
		context.assertIsSatisfied();
	}

	@Test
	public void constructor() {
	}
}
