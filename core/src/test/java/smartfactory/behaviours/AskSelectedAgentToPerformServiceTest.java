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

public class AskSelectedAgentToPerformServiceTest {

	private final Mockery context = new Mockery() {
		{
			this.setImposteriser(ClassImposteriser.INSTANCE);
		}
	};

	Agent agent_mock;
	ProductDataStore productDataStore_mock;
	Behaviour behaviour_mock;

	AskSelectedAgentToPerformService askSelectedAgentToPerformService;

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

		askSelectedAgentToPerformService = new AskSelectedAgentToPerformService(behaviour_mock);
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
