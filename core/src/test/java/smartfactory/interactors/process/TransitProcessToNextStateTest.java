package smartfactory.interactors.process;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.lib.legacy.ClassImposteriser;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import smartfactory.dataStores.ProcessDataStore;
import smartfactory.interactors.process.TransitProcessToNextState;
import smartfactory.models.Process;

public class TransitProcessToNextStateTest {

	private final Mockery context = new Mockery() {
		{
			this.setImposteriser(ClassImposteriser.INSTANCE);
		}
	};

	ProcessDataStore dataStore_mock;

	TransitProcessToNextState testable;

	@Before
	public void setUp() {
		dataStore_mock = context.mock(ProcessDataStore.class);

		testable = new TransitProcessToNextState(dataStore_mock);
	}

	@After
	public void tearDown() {
		context.assertIsSatisfied();
	}

	@Test
	public void execute() {
		Process process_mock = context.mock(Process.class);

		context.checking(new Expectations() {
			{
				oneOf(dataStore_mock).getProcess();
				will(returnValue(process_mock));

				oneOf(process_mock).moveToNextState();
			}
		});

		testable.execute();
	}

	@Test
	public void next() {
		Process process_mock = context.mock(Process.class);

		context.checking(new Expectations() {
			{
				oneOf(dataStore_mock).getProcess();
				will(returnValue(process_mock));

				oneOf(process_mock).isInTheLastState();
				will(returnValue(Process.IsInTheLastState));
			}
		});

		Assert.assertEquals(Process.IsInTheLastState, testable.next());
	}
}
