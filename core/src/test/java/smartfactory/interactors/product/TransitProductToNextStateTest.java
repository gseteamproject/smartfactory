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

	ProductDataStore productDataStore_mock;

	TransitProductToNextState transitProductToNextState;

	@Before
	public void setUp() {
		productDataStore_mock = context.mock(ProductDataStore.class);

		transitProductToNextState = new TransitProductToNextState(productDataStore_mock);
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
				oneOf(productDataStore_mock).getProduct();
				will(returnValue(product_mock));
				
				oneOf(product_mock).moveToNextState();
			}
		});

		transitProductToNextState.execute();
	}

	@Test
	public void next() {
		Product product_mock = context.mock(Product.class);
		
		context.checking(new Expectations() {
			{
				oneOf(productDataStore_mock).getProduct();
				will(returnValue(product_mock));
				
				oneOf(product_mock).isInTheLastState();
				will(returnValue(Product.IsInTheLastState));
			}
		});

		Assert.assertEquals(Product.IsInTheLastState, transitProductToNextState.next());
	}
}
