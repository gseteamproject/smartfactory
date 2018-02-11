package smartfactory.behaviours.process;

import org.jmock.Mockery;
import org.jmock.lib.legacy.ClassImposteriser;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import smartfactory.behaviours.process.NoAgentsProvidingServiceBehaviour;
import smartfactory.dataStores.ProcessDataStore;

public class NoAgentsProvidingServiceBehaviourTest {

	private final Mockery context = new Mockery() {
		{
			this.setImposteriser(ClassImposteriser.INSTANCE);
		}
	};

	ProcessDataStore dataStore_mock;

	NoAgentsProvidingServiceBehaviour testable;

	@Before
	public void setUp() {
		dataStore_mock = context.mock(ProcessDataStore.class);

		testable = new NoAgentsProvidingServiceBehaviour(dataStore_mock);
	}

	@After
	public void tearDown() {
		context.assertIsSatisfied();
	}

	@Test
	public void constructor() {
	}
}
