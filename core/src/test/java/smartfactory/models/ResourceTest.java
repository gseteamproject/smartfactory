package smartfactory.models;

import org.jmock.Mockery;
import org.jmock.lib.legacy.ClassImposteriser;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

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
	public void execute() {
		testable.execute("operation");
	}

	@Test
	public void willExecute() {
		Assert.assertTrue(testable.willExecute("operation"));
	}

	@Test
	public void getStatus() {
		testable.status("operation");
	}

	@Test
	public void terminate() {
		testable.terminate("operation");
	}
}
