package smartfactory.models;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.lib.legacy.ClassImposteriser;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ResourceOperationTest {

	private final Mockery context = new Mockery() {
		{
			this.setImposteriser(ClassImposteriser.INSTANCE);
		}
	};

	ResourceOperation testable;

	String operationName;

	@Before
	public void setUp() {
		operationName = "operation";

		testable = new ResourceOperation(operationName);
	}

	@After
	public void tearDown() {
		context.assertIsSatisfied();
	}

	@Test
	public void prepare() {
		testable.prepare();

		Assert.assertFalse(testable.executed);
	}

	@Test
	public void execute() {
		Resource resource_mock = context.mock(Resource.class);

		context.checking(new Expectations() {
			{
				oneOf(resource_mock).notifyAll(Event.OPERATION_COMPLETED_SUCCESS);
			}
		});

		testable.setResource(resource_mock);

		testable.execute();
		Assert.assertTrue(testable.executed);
	}

	@Test
	public void terminate() {
		Resource resource_mock = context.mock(Resource.class);

		context.checking(new Expectations() {
			{
				oneOf(resource_mock).notifyAll(Event.OPERATION_COMPLETED_FAILURE);
			}
		});

		testable.setResource(resource_mock);

		testable.terminate();
		Assert.assertTrue(testable.executed);
	}
}
