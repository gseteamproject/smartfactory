package smartfactory.behaviours.process;

import org.jmock.Mockery;
import org.jmock.lib.legacy.ClassImposteriser;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import smartfactory.utility.AgentDataStore;

public class ProcessBehaviourTest {

	private final Mockery context = new Mockery() {
		{
			this.setImposteriser(ClassImposteriser.INSTANCE);
		}
	};

	AgentDataStore dataStore_mock;

	ProcessBehaviour testable;

	@Before
	public void setUp() {
		dataStore_mock = context.mock(AgentDataStore.class);

		testable = new ProcessBehaviour(dataStore_mock);
	}

	@After
	public void tearDown() {
		context.assertIsSatisfied();
	}

	@Test
	public void constructor() {
		Assert.assertNotNull(testable);
	}
}
