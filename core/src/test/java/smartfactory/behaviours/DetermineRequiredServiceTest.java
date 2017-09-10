package smartfactory.behaviours;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.lib.legacy.ClassImposteriser;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import jade.core.behaviours.DataStore;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import smartfactory.matchers.ServiceDescriptionMatcher;
import smartfactory.models.Product;

public class DetermineRequiredServiceTest {

	private final Mockery context = new Mockery() {
		{
			this.setImposteriser(ClassImposteriser.INSTANCE);
		}
	};

	Agent agent_mock;
	DataStore dataStore_mock;
	Behaviour behaviour_mock;

	DetermineRequiredService determineRequiredService;

	@Before
	public void setUp() {
		agent_mock = context.mock(Agent.class);
		dataStore_mock = context.mock(DataStore.class);
		behaviour_mock = context.mock(Behaviour.class);

		context.checking(new Expectations() {
			{
				oneOf(behaviour_mock).getAgent();
				will(returnValue(agent_mock));

				oneOf(behaviour_mock).getDataStore();
				will(returnValue(dataStore_mock));
				
				oneOf(behaviour_mock).getDataStore();
				will(returnValue(dataStore_mock));
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
				oneOf(dataStore_mock).get("product");
				will(returnValue(product_mock));

				oneOf(product_mock).getRequiredServiceName();
				will(returnValue(serviceName));

				oneOf(dataStore_mock).put(with("requiredService"),
						with(new ServiceDescriptionMatcher().expectName(serviceName)));
			}
		});

		determineRequiredService.action();
	}
}
