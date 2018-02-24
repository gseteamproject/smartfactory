package smartfactory.models;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import smartfactory.services.Services;

public class BlockTest {

	BlockProcess testable;

	@Before
	public void setUp() {
		testable = new BlockProcess();
	}

	@Test
	public void constructor() {
		Assert.assertEquals(Services.store, testable.getProcessOperation().serviceName);
		testable.moveToNextOperation();

		Assert.assertEquals(Services.recognition, testable.getProcessOperation().serviceName);
		testable.moveToNextOperation();

		Assert.assertEquals(Services.cleaning, testable.getProcessOperation().serviceName);
		testable.moveToNextOperation();

		Assert.assertEquals(Services.painting, testable.getProcessOperation().serviceName);
		testable.moveToNextOperation();

		Assert.assertEquals(Services.packing, testable.getProcessOperation().serviceName);
		testable.moveToNextOperation();
	}
}
