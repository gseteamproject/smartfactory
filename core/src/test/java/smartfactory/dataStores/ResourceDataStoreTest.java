package smartfactory.dataStores;

import org.jmock.Mockery;
import org.jmock.lib.legacy.ClassImposteriser;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import smartfactory.models.Resource;

public class ResourceDataStoreTest {
	private final Mockery context = new Mockery() {
		{
			this.setImposteriser(ClassImposteriser.INSTANCE);
		}
	};

	ResourceDataStore testable;

	@Before
	public void setUp() {
		testable = new ResourceDataStore();
	}

	@After
	public void tearDown() {
		context.assertIsSatisfied();
	}

	@Test
	public void getMachine() {
		final Resource resource_mock = context.mock(Resource.class);

		testable.put("resource", resource_mock);

		Assert.assertEquals(resource_mock, testable.getResource());
	}

	@Test
	public void setMachine() {
		final Resource resource_mock = context.mock(Resource.class);

		testable.setResource(resource_mock);

		Assert.assertEquals(resource_mock, testable.get("resource"));
	}
}
