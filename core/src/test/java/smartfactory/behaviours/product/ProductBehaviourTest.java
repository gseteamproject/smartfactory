package smartfactory.behaviours.product;

import org.jmock.Mockery;
import org.jmock.lib.legacy.ClassImposteriser;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import smartfactory.behaviours.product.ProductBehaviour;
import smartfactory.dataStores.ProductDataStore;

public class ProductBehaviourTest {

	private final Mockery context = new Mockery() {
		{
			this.setImposteriser(ClassImposteriser.INSTANCE);
		}
	};

	ProductDataStore dataStore_mock;

	ProductBehaviour testable;

	@Before
	public void setUp() {
		dataStore_mock = context.mock(ProductDataStore.class);

		testable = new ProductBehaviour(dataStore_mock);
	}

	@After
	public void tearDown() {
		context.assertIsSatisfied();
	}

	@Test
	public void constructor() {
		Assert.assertNotNull(testable);
	}
}
