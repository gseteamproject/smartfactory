package smartfactory.interactors.product;

import org.jmock.Mockery;
import org.jmock.lib.legacy.ClassImposteriser;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import smartfactory.dataStores.ProductDataStore;

public class ProductProcessIsIncorrectTest {
	private final Mockery context = new Mockery() {
		{
			this.setImposteriser(ClassImposteriser.INSTANCE);
		}
	};

	ProductDataStore productDataStore_mock;

	ProductProcessIsIncorrect productProcessIsIncorrect;

	@Before
	public void setUp() {
		productDataStore_mock = context.mock(ProductDataStore.class);

		productProcessIsIncorrect = new ProductProcessIsIncorrect(productDataStore_mock);
	}

	@After
	public void tearDown() {
		context.assertIsSatisfied();
	}

	@Test
	public void execute() {
		productProcessIsIncorrect.execute();
	}

	@Test
	public void next() {
		Assert.assertEquals(0, productProcessIsIncorrect.next());
	}
}
