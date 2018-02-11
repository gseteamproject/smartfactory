package smartfactory.behaviours.resource;

import org.jmock.Mockery;
import org.jmock.lib.legacy.ClassImposteriser;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import smartfactory.behaviours.resource.ServiceProvisioningResponderBehaviour;
import smartfactory.dataStores.ResourceDataStore;

public class ServiceProvisioningResponderBehaviourTest {

	private final Mockery context = new Mockery() {
		{
			this.setImposteriser(ClassImposteriser.INSTANCE);
		}
	};

	ResourceDataStore dataStore_mock;

	ServiceProvisioningResponderBehaviour testable;

	@Before
	public void setUp() {
		dataStore_mock = context.mock(ResourceDataStore.class);

		testable = new ServiceProvisioningResponderBehaviour(dataStore_mock);
	}

	@After
	public void tearDown() {
		context.assertIsSatisfied();
	}

	@Test
	public void constructor() {
	}
}
