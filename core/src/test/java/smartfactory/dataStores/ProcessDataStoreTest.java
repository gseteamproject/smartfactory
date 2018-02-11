package smartfactory.dataStores;

import org.jmock.Mockery;
import org.jmock.lib.legacy.ClassImposteriser;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import smartfactory.models.ProcessOperation;
import smartfactory.models.Process;

public class ProcessDataStoreTest {

	private final Mockery context = new Mockery() {
		{
			this.setImposteriser(ClassImposteriser.INSTANCE);
		}
	};

	ProcessDataStore testable;

	@Before
	public void setUp() {
		testable = new ProcessDataStore();
	}

	@After
	public void tearDown() {
		context.assertIsSatisfied();
	}

	@Test
	public void getProcess() {
		final Process mock = context.mock(Process.class);

		testable.put("process", mock);

		Assert.assertEquals(mock, testable.getProcess());
	}

	@Test
	public void setProcess() {
		final Process mock = context.mock(Process.class);

		testable.setProcess(mock);

		Assert.assertEquals(mock, testable.get("process"));
	}

	@Test
	public void getProcessOperation() {
		final ProcessOperation mock = context.mock(ProcessOperation.class);

		testable.put("process-operation", mock);

		Assert.assertEquals(mock, testable.getProcessOperation());
	}

	@Test
	public void setProcessOperation() {
		final ProcessOperation mock = context.mock(ProcessOperation.class);

		testable.setProcessOperation(mock);

		Assert.assertEquals(mock, testable.get("process-operation"));
	}
}
