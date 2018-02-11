package smartfactory.behaviours.process;

import org.jmock.Mockery;
import org.jmock.lib.legacy.ClassImposteriser;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import smartfactory.behaviours.process.FindAgentsProvidingServiceBehaviour;
import smartfactory.dataStores.ProcessDataStore;

public class FindAgentsProvidingServiceBehaviourTest {

	private final Mockery context = new Mockery() {
		{
			this.setImposteriser(ClassImposteriser.INSTANCE);
		}
	};

	ProcessDataStore dataStore_mock;

	FindAgentsProvidingServiceBehaviour testable;

	@Before
	public void setUp() {
		dataStore_mock = context.mock(ProcessDataStore.class);

		testable = new FindAgentsProvidingServiceBehaviour(dataStore_mock);
	}

	@After
	public void tearDown() {
		context.assertIsSatisfied();
	}

	@Test
	public void constructor() {
	}
}
