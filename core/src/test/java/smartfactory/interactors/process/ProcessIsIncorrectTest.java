package smartfactory.interactors.process;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.lib.legacy.ClassImposteriser;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import smartfactory.eventSubscription.EventSubscribers;
import smartfactory.eventSubscription.ontology.Event;
import smartfactory.interactors.process.ProcessIsIncorrect;
import smartfactory.utility.AgentDataStore;

public class ProcessIsIncorrectTest {

	private final Mockery context = new Mockery() {
		{
			this.setImposteriser(ClassImposteriser.INSTANCE);
		}
	};

	AgentDataStore dataStore_mock;

	ProcessIsIncorrect testable;

	@Before
	public void setUp() {
		dataStore_mock = context.mock(AgentDataStore.class);

		testable = new ProcessIsIncorrect(dataStore_mock);
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

				// TODO: add Event matcher
				oneOf(eventSubscribers_mock).notifyAll(with(any(Event.class)));
			}
		});

		testable.execute();
	}

	@Test
	public void next() {
		Assert.assertEquals(0, testable.next());
	}
}
