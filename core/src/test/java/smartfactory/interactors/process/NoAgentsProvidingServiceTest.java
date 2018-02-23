package smartfactory.interactors.process;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.lib.legacy.ClassImposteriser;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import smartfactory.interactors.process.NoAgentsProvidingService;
import smartfactory.models.Event;
import smartfactory.utility.AgentDataStore;
import smartfactory.utility.EventSubscribers;

public class NoAgentsProvidingServiceTest {

	private final Mockery context = new Mockery() {
		{
			this.setImposteriser(ClassImposteriser.INSTANCE);
		}
	};

	AgentDataStore dataStore_mock;

	NoAgentsProvidingService testable;

	@Before
	public void setUp() {
		dataStore_mock = context.mock(AgentDataStore.class);

		testable = new NoAgentsProvidingService(dataStore_mock);
	}

	@After
	public void tearDown() {
		context.assertIsSatisfied();
	}

	@Test
	public void execute() {
		EventSubscribers eventSubscribers_mock = context.mock(EventSubscribers.class);

		context.checking(new Expectations() {
			{
				oneOf(dataStore_mock).getEventSubsribers();
				will(returnValue(eventSubscribers_mock));

				oneOf(eventSubscribers_mock).notifyAll(Event.PROCESS_COMPLETED_FAILURE);
			}
		});

		testable.execute();
	}

	@Test
	public void next() {
		Assert.assertEquals(0, testable.next());
	}
}
