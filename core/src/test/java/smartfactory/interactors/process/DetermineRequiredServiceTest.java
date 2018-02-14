package smartfactory.interactors.process;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.lib.legacy.ClassImposteriser;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import smartfactory.dataStores.ProcessDataStore;
import smartfactory.interactors.process.DetermineRequiredService;
import smartfactory.models.ProcessOperation;
import smartfactory.models.Process;

public class DetermineRequiredServiceTest {

	private final Mockery context = new Mockery() {
		{
			this.setImposteriser(ClassImposteriser.INSTANCE);
		}
	};

	ProcessDataStore dataStore_mock;

	DetermineRequiredService testable;

	@Before
	public void setUp() {
		dataStore_mock = context.mock(ProcessDataStore.class);

		testable = new DetermineRequiredService(dataStore_mock);
	}

	@After
	public void tearDown() {
		context.assertIsSatisfied();
	}

	@Test
	public void execute() {
		final Process process_mock = context.mock(Process.class);
		final ProcessOperation operation_mock = context.mock(ProcessOperation.class);

		context.checking(new Expectations() {
			{
				oneOf(dataStore_mock).getProcess();
				will(returnValue(process_mock));

				oneOf(process_mock).getProcessOperation();
				will(returnValue(operation_mock));

				oneOf(dataStore_mock).setProcessOperation(operation_mock);
			}
		});

		testable.execute();
	}

	@Test
	public void next() {
		final ProcessOperation operation_mock = context.mock(ProcessOperation.class);
		final int isServiceDetermined = ProcessOperation.ServiceDetermined;

		context.checking(new Expectations() {
			{
				oneOf(dataStore_mock).getProcessOperation();
				will(returnValue(operation_mock));

				oneOf(operation_mock).isServiceDetermined();
				will(returnValue(isServiceDetermined));
			}
		});

		Assert.assertEquals(isServiceDetermined, testable.next());
	}
}
