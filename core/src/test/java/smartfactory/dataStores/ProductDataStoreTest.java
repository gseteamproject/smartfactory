package smartfactory.dataStores;

import org.jmock.Mockery;
import org.jmock.lib.legacy.ClassImposteriser;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import smartfactory.dataStores.ProductDataStore;
import smartfactory.models.Product;

public class ProductDataStoreTest {

	private final Mockery context = new Mockery() {
		{
			this.setImposteriser(ClassImposteriser.INSTANCE);
		}
	};

	ProductDataStore testable;

	@Before
	public void setUp() {
		testable = new ProductDataStore();
	}

	@After
	public void tearDown() {
		context.assertIsSatisfied();
	}

	@Test
	public void getProduct() {
		final Product mock = context.mock(Product.class);

		testable.put("product", mock);

		Assert.assertEquals(mock, testable.getProduct());
	}

	@Test
	public void setProduct() {
		final Product mock = context.mock(Product.class);

		testable.setProduct(mock);

		Assert.assertEquals(mock, testable.get("product"));
	}
}
