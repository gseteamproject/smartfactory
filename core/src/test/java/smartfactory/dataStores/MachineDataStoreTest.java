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

	MachineDataStore machineDataStore;

	@Before
	public void setUp() {
		machineDataStore = new MachineDataStore();
	}

	@After
	public void tearDown() {
		context.assertIsSatisfied();
	}

	@Test
	public void getMachine() {
		final Machine machine_mock = context.mock(Machine.class);

		machineDataStore.put("machine", machine_mock);

		Assert.assertEquals(machine_mock, machineDataStore.getMachine());
	}

	@Test
	public void setMachine() {
		final Machine machine_mock = context.mock(Machine.class);

		machineDataStore.setMachine(machine_mock);

		Assert.assertEquals(machine_mock, machineDataStore.get("machine"));
	}
}
