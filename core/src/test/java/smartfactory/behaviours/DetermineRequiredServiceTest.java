package smartfactory.behaviours;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.lib.legacy.ClassImposteriser;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import smartfactory.dataStores.ProductDataStore;
import smartfactory.matchers.ServiceDescriptionMatcher;
import smartfactory.models.Product;

public class DetermineRequiredServiceTest {

	private final Mockery context = new Mockery() {
		{
			this.setImposteriser(ClassImposteriser.INSTANCE);
		}
	};

	Agent agent_mock;
	ProductDataStore productDataStore_mock;
	Behaviour behaviour_mock;

	DetermineRequiredService determineRequiredService;

	@Before
	public void setUp() {
		agent_mock = context.mock(Agent.class);
		productDataStore_mock = context.mock(ProductDataStore.class);
		behaviour_mock = context.mock(Behaviour.class);

		context.checking(new Expectations() {
			{
				oneOf(behaviour_mock).getAgent();
				will(returnValue(agent_mock));

				oneOf(behaviour_mock).getDataStore();
				will(returnValue(productDataStore_mock));
			}
		});

		determineRequiredService = new DetermineRequiredService(behaviour_mock);
	}

	@After
	public void tearDown() {
		context.assertIsSatisfied();
	}

	@Test
	public void action() {
		final Product product_mock = context.mock(Product.class);
		final String serviceName = "serviceName";

		context.checking(new Expectations() {
			{
				oneOf(productDataStore_mock).getProduct();
				will(returnValue(product_mock));

				oneOf(product_mock).getRequiredServiceName();
				will(returnValue(serviceName));

				oneOf(productDataStore_mock)
						.setRequiredService(with(new ServiceDescriptionMatcher().expectName(serviceName)));
			}
		});

		determineRequiredService.action();
	}
}
