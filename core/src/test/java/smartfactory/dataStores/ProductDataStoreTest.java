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

	ProductDataStore productDataStore;

	@Before
	public void setUp() {
		productDataStore = new ProductDataStore();
	}

	@After
	public void tearDown() {
		context.assertIsSatisfied();
	}

	@Test
	public void getProduct() {
		final Product product_mock = context.mock(Product.class);

		productDataStore.put("product", product_mock);

		Assert.assertEquals(product_mock, productDataStore.getProduct());
	}

	@Test
	public void setProduct() {
		final Product product_mock = context.mock(Product.class);

		productDataStore.setProduct(product_mock);

		Assert.assertEquals(product_mock, productDataStore.get("product"));
	}
}
