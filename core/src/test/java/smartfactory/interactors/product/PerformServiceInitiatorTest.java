package smartfactory.interactors.product;

import org.jmock.Mockery;
import org.jmock.lib.legacy.ClassImposteriser;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import smartfactory.dataStores.ProductDataStore;
import smartfactory.interactors.product.PerformServiceInitiator;

public class PerformServiceInitiatorTest {

	private final Mockery context = new Mockery() {
		{
			this.setImposteriser(ClassImposteriser.INSTANCE);
		}
	};

	ProductDataStore productDataStore_mock;

	PerformServiceInitiator askSelectedAgentToPerformService;

	@Before
	public void setUp() {
		productDataStore_mock = context.mock(ProductDataStore.class);

		askSelectedAgentToPerformService = new PerformServiceInitiator(productDataStore_mock);
	}

	@After
	public void tearDown() {
		context.assertIsSatisfied();
	}

	@Test
	public void removeAgentProvidingService() {
		// TODO : implement
	}
}
