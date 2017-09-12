package smartfactory.behaviours;

import org.jmock.Mockery;
import org.jmock.lib.legacy.ClassImposteriser;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import jade.core.Agent;
import smartfactory.models.Product;

public class ProductMainBehaviourTest {

	private final Mockery context = new Mockery() {
		{
			this.setImposteriser(ClassImposteriser.INSTANCE);
		}
	};

	Agent agent_mock;
	Product product_mock;

	ProductMainBehaviour productMainBehaviour;

	@Before
	public void setUp() {
		agent_mock = context.mock(Agent.class);
		product_mock = context.mock(Product.class);

		productMainBehaviour = new ProductMainBehaviour(agent_mock, product_mock);
	}

	@After
	public void tearDown() {
		context.assertIsSatisfied();
	}

	@Test
	public void constructor() {
		Assert.assertNotNull(productMainBehaviour);
	}
}
