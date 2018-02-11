package smartfactory.interactors.process;

import org.jmock.Mockery;
import org.jmock.lib.legacy.ClassImposteriser;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import smartfactory.dataStores.ProcessDataStore;
import smartfactory.interactors.process.NoAgentsProvidingService;

public class NoAgentsProvidingServiceTest {

	private final Mockery context = new Mockery() {
		{
			this.setImposteriser(ClassImposteriser.INSTANCE);
		}
	};

	ProcessDataStore dataStore_mock;

	NoAgentsProvidingService testable;

	@Before
	public void setUp() {
		dataStore_mock = context.mock(ProcessDataStore.class);

		testable = new NoAgentsProvidingService(dataStore_mock);
	}

	@After
	public void tearDown() {
		context.assertIsSatisfied();
	}

	@Test
	public void execute() {
		testable.execute();
	}

	@Test
	public void next() {
		Assert.assertEquals(0, testable.next());
	}
}
