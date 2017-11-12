package smartfactory.interactors.product;

import org.jmock.Mockery;
import org.jmock.lib.legacy.ClassImposteriser;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import smartfactory.dataStores.ProductDataStore;

public class ProductIsInLastStateTest {
	private final Mockery context = new Mockery() {
		{
			this.setImposteriser(ClassImposteriser.INSTANCE);
		}
	};

	ProductDataStore productDataStore_mock;

	ProductIsInLastState testable;

	@Before
	public void setUp() {
		productDataStore_mock = context.mock(ProductDataStore.class);

		testable = new ProductIsInLastState(productDataStore_mock);
	}

	@After
	public void tearDown() {
		context.assertIsSatisfied();
	}

	@Test
	public void execute() {
		testable.execute();
	}

	@Test
	public void next() {
		Assert.assertEquals(0, testable.next());
	}
}
