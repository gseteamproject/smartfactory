package smartfactory.interactors.product;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.lib.legacy.ClassImposteriser;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import smartfactory.dataStores.ProductDataStore;
import smartfactory.models.Product;

public class TransitProductToNextStateTest {

	private final Mockery context = new Mockery() {
		{
			this.setImposteriser(ClassImposteriser.INSTANCE);
		}
	};

	ProductDataStore dataStore_mock;

	TransitProductToNextState testable;

	@Before
	public void setUp() {
		dataStore_mock = context.mock(ProductDataStore.class);

		testable = new TransitProductToNextState(dataStore_mock);
	}

	@After
	public void tearDown() {
		context.assertIsSatisfied();
	}

	@Test
	public void execute() {
		Product product_mock = context.mock(Product.class);

		context.checking(new Expectations() {
			{
				oneOf(dataStore_mock).getProduct();
				will(returnValue(product_mock));

				oneOf(product_mock).moveToNextState();
			}
		});

		testable.execute();
	}

	@Test
	public void next() {
		Product product_mock = context.mock(Product.class);

		context.checking(new Expectations() {
			{
				oneOf(dataStore_mock).getProduct();
				will(returnValue(product_mock));

				oneOf(product_mock).isInTheLastState();
				will(returnValue(Product.IsInTheLastState));
			}
		});

		Assert.assertEquals(Product.IsInTheLastState, testable.next());
	}
}
