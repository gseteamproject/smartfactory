package smartfactory.models;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.lib.legacy.ClassImposteriser;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import jade.domain.FIPAAgentManagement.ServiceDescription;
import smartfactory.utility.AgentDataStore;
import smartfactory.utility.EventSubscribers;

public class AgentServiceTest {

	private final Mockery context = new Mockery() {
		{
			this.setImposteriser(ClassImposteriser.INSTANCE);
		}
	};

	AgentService testable;

	AgentDataStore agentDataStore_mock;

	String operationName;

	@Before
	public void setUp() {
		agentDataStore_mock = context.mock(AgentDataStore.class);
		operationName = "operation";

		testable = new AgentService(operationName, agentDataStore_mock);
	}

	@After
	public void tearDown() {
		context.assertIsSatisfied();
	}

	@Test
	public void getServiceDescription() {
		ServiceDescription serviceDescription = testable.getServiceDescription();
		Assert.assertEquals(operationName, serviceDescription.getName());
		Assert.assertEquals("", serviceDescription.getType());
	}

	@Test
	public void execute() {
		Resource resource_mock = context.mock(Resource.class);
		EventSubscribers eventSubscribers_mock = context.mock(EventSubscribers.class);

		context.checking(new Expectations() {
			{
				oneOf(agentDataStore_mock).getResource();
				will(returnValue(resource_mock));

				oneOf(resource_mock).execute(operationName);

				oneOf(agentDataStore_mock).getEventSubsribers();
				will(returnValue(eventSubscribers_mock));

				// TODO : add Event matcher
				oneOf(eventSubscribers_mock).notifyAll(with(any(Event.class)));
			}
		});

		testable.execute();
	}

	@Test
	public void terminate() {
		Resource resource_mock = context.mock(Resource.class);
		EventSubscribers eventSubscribers_mock = context.mock(EventSubscribers.class);

		context.checking(new Expectations() {
			{
				oneOf(agentDataStore_mock).getResource();
				will(returnValue(resource_mock));

				oneOf(resource_mock).terminate(operationName);

				oneOf(agentDataStore_mock).getEventSubsribers();
				will(returnValue(eventSubscribers_mock));

				// TODO : add Event matcher
				oneOf(eventSubscribers_mock).notifyAll(with(any(Event.class)));
			}
		});

		testable.terminate();
	}
}
