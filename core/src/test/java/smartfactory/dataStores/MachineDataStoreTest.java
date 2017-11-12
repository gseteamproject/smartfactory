package smartfactory.dataStores;

import org.jmock.Mockery;
import org.jmock.lib.legacy.ClassImposteriser;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import smartfactory.models.Machine;

public class MachineDataStoreTest {
	private final Mockery context = new Mockery() {
		{
			this.setImposteriser(ClassImposteriser.INSTANCE);
		}
	};

	MachineDataStore testable;

	@Before
	public void setUp() {
		testable = new MachineDataStore();
	}

	@After
	public void tearDown() {
		context.assertIsSatisfied();
	}

	@Test
	public void getMachine() {
		final Machine machine_mock = context.mock(Machine.class);

		testable.put("machine", machine_mock);

		Assert.assertEquals(machine_mock, testable.getMachine());
	}

	@Test
	public void setMachine() {
		final Machine machine_mock = context.mock(Machine.class);

		testable.setMachine(machine_mock);

		Assert.assertEquals(machine_mock, testable.get("machine"));
	}
}
