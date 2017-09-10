package smartfactory.behaviours;

import org.jmock.Mockery;
import org.jmock.lib.legacy.ClassImposteriser;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import jade.core.Agent;

public class ProductMainBehaviourTest {

	private final Mockery context = new Mockery() {
		{
			this.setImposteriser(ClassImposteriser.INSTANCE);
		}
	};

	Agent agent_mock;

	ProductMainBehaviour productMainBehaviour;

	@Before
	public void setUp() {
		agent_mock = context.mock(Agent.class);

		productMainBehaviour = new ProductMainBehaviour(agent_mock);
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
