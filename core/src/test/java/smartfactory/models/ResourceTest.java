package smartfactory.models;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.lib.legacy.ClassImposteriser;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import smartfactory.utility.EventSubscribers;

public class ResourceTest {

	private final Mockery context = new Mockery() {
		{
			this.setImposteriser(ClassImposteriser.INSTANCE);
		}
	};

	Resource testable;

	@Before
	public void setUp() {
		testable = new Resource();
	}

	@After
	public void tearDown() {
		context.assertIsSatisfied();
	}

	@Test
	public void addOperation() {
		ResourceOperation operation_mock = context.mock(ResourceOperation.class);

		context.checking(new Expectations() {
			{
				oneOf(operation_mock).setResource(testable);
			}
		});

		testable.addOperation(operation_mock);
	}

	@Test
	public void getOperations() {
		ResourceOperation operation_1_mock = context.mock(ResourceOperation.class, "operation-1");
		operation_1_mock.name = "operation-1";
		ResourceOperation operation_2_mock = context.mock(ResourceOperation.class, "operation-2");
		operation_2_mock.name = "operation-2";

		context.checking(new Expectations() {
			{
				oneOf(operation_1_mock).setResource(testable);

				oneOf(operation_2_mock).setResource(testable);
			}
		});

		testable.addOperation(operation_1_mock);
		testable.addOperation(operation_2_mock);

		Assert.assertArrayEquals(new String[] { "operation-1", "operation-2" }, testable.getOperations());
	}

	@Test
	public void execute() {
		ResourceOperation operation_mock = context.mock(ResourceOperation.class);
		operation_mock.name = "operation";

		context.checking(new Expectations() {
			{
				oneOf(operation_mock).setResource(testable);

				oneOf(operation_mock).prepare();

				oneOf(operation_mock).execute();
			}
		});

		testable.addOperation(operation_mock);

		testable.execute("operation");
	}

	@Test
	public void willExecute() {
		Assert.assertTrue(testable.willExecute("operation"));
	}

	@Test
	public void getStatus() {
		testable.getStatus();
	}

	@Test
	public void terminate() {
		ResourceOperation operation_mock = context.mock(ResourceOperation.class);
		operation_mock.name = "operation";

		context.checking(new Expectations() {
			{
				oneOf(operation_mock).setResource(testable);

				oneOf(operation_mock).terminate();
			}
		});

		testable.addOperation(operation_mock);

		testable.terminate("operation");
	}

	@Test
	public void setEventSubscribers() {
		EventSubscribers eventSubscribers_mock = context.mock(EventSubscribers.class);

		testable.setEventSubscribers(eventSubscribers_mock);
	}

	@Test
	public void notifyAllAboutEvent() {
		EventSubscribers eventSubscribers_mock = context.mock(EventSubscribers.class);

		context.checking(new Expectations() {
			{
				oneOf(eventSubscribers_mock).notifyAll("event");
			}
		});

		testable.setEventSubscribers(eventSubscribers_mock);

		testable.notifyAll("event");
	}
}
