package smartfactory.behaviours;

import java.util.Arrays;
import java.util.List;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.lib.legacy.ClassImposteriser;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import smartfactory.dataStores.ProductDataStore;
import smartfactory.models.Order;

public class SelectAgentToPerformServiceTest {

	private final Mockery context = new Mockery() {
		{
			this.setImposteriser(ClassImposteriser.INSTANCE);
		}
	};

	Agent agent_mock;

	ProductDataStore productDataStore_mock;
	Behaviour behaviour_mock;

	SelectAgentToPerformServiceBehaviour selectAgentToPerformService;

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

		selectAgentToPerformService = new SelectAgentToPerformServiceBehaviour(behaviour_mock);
	}

	@After
	public void tearDown() {
		context.assertIsSatisfied();
	}

	@Test
	public void action_agentSelected() {
		final DFAgentDescription agentProvidingService = new DFAgentDescription();
		final List<DFAgentDescription> agentsProvidingService = Arrays
				.asList(new DFAgentDescription[] { agentProvidingService });
		final Order order_mock = context.mock(Order.class);
		order_mock.agentsDescription = agentsProvidingService;

		context.checking(new Expectations() {
			{
				oneOf(productDataStore_mock).getOrder();
				will(returnValue(order_mock));

				oneOf(productDataStore_mock).setAgentProvidingService(agentProvidingService);
			}
		});

		selectAgentToPerformService.action();
		Assert.assertEquals(SelectAgentToPerformServiceBehaviour.AgentSelected, selectAgentToPerformService.onEnd());
	}

	@Test
	public void action_agentNotSelected() {
		final List<DFAgentDescription> agentsProvidingService = Arrays.asList(new DFAgentDescription[] {});
		final Order order_mock = context.mock(Order.class);
		order_mock.agentsDescription = agentsProvidingService;

		context.checking(new Expectations() {
			{
				oneOf(productDataStore_mock).getOrder();
				will(returnValue(order_mock));

				oneOf(productDataStore_mock).setAgentProvidingService(null);
			}
		});

		selectAgentToPerformService.action();
		Assert.assertEquals(SelectAgentToPerformServiceBehaviour.AgentNotSelected, selectAgentToPerformService.onEnd());
	}
}
