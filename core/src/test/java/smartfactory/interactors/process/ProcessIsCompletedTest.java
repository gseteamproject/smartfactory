package smartfactory.interactors.process;

import org.jmock.Mockery;
import org.jmock.lib.legacy.ClassImposteriser;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import smartfactory.interactors.process.ProcessIsCompleted;
import smartfactory.utility.AgentDataStore;

public class ProcessIsCompletedTest {

	private final Mockery context = new Mockery() {
		{
			this.setImposteriser(ClassImposteriser.INSTANCE);
		}
	};

	AgentDataStore dataStore_mock;

	ProcessIsCompleted testable;

	@Before
	public void setUp() {
		dataStore_mock = context.mock(AgentDataStore.class);

		testable = new ProcessIsCompleted(dataStore_mock);
	}

	@After
	public void tearDown() {
		context.assertIsSatisfied();
	}

	@Test
	@Ignore
	public void execute() {
		// TODO : fix
		testable.execute();
	}

	@Test
	public void next() {
		Assert.assertEquals(0, testable.next());
	}
}
