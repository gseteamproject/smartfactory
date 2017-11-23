package smartfactory.behaviours.product;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.lib.legacy.ClassImposteriser;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import jade.core.Agent;
import smartfactory.behaviours.product.ProductBehaviour;
import smartfactory.dataStores.ProductDataStore;
import smartfactory.platform.AgentPlatform;

public class ProductBehaviourTest {

	private final Mockery context = new Mockery() {
		{
			this.setImposteriser(ClassImposteriser.INSTANCE);
		}
	};

	AgentPlatform agentPlatform_mock;
	Agent agent_mock;
	ProductDataStore dataStore_mock;

	ProductBehaviour testable;

	@Before
	public void setUp() {
		agent_mock = context.mock(Agent.class);
		agentPlatform_mock = context.mock(AgentPlatform.class);
		dataStore_mock = context.mock(ProductDataStore.class);

		context.checking(new Expectations() {
			{
				oneOf(dataStore_mock).getAgentPlatform();
				will(returnValue(agentPlatform_mock));

				oneOf(agentPlatform_mock).getThisAgent();
				will(returnValue(agent_mock));
			}
		});

		testable = new ProductBehaviour(dataStore_mock);
	}

	@After
	public void tearDown() {
		context.assertIsSatisfied();
	}

	@Test
	public void constructor() {
		Assert.assertNotNull(testable);
	}
}
