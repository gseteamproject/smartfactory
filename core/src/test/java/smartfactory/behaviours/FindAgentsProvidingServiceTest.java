package smartfactory.behaviours;

import java.util.List;

import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.lib.legacy.ClassImposteriser;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import jade.domain.FIPAException;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import smartfactory.dataStores.ProductDataStore;
import smartfactory.models.Order;
import smartfactory.platform.AgentPlatform;

public class FindAgentsProvidingServiceTest {
	private final Mockery context = new Mockery() {
		{
			this.setImposteriser(ClassImposteriser.INSTANCE);
		}
	};

	Agent agent_mock;
	ProductDataStore productDataStore_mock;
	Behaviour behaviour_mock;
	AgentPlatform jadePlatform_mock;

	FindAgentsProvidingServiceBehaviour findAgentsProvidingService;

	@Before
	public void setUp() {
		agent_mock = context.mock(Agent.class);
		productDataStore_mock = context.mock(ProductDataStore.class);
		behaviour_mock = context.mock(Behaviour.class);
		jadePlatform_mock = context.mock(AgentPlatform.class);

		context.checking(new Expectations() {
			{
				oneOf(behaviour_mock).getAgent();
				will(returnValue(agent_mock));

				oneOf(behaviour_mock).getDataStore();
				will(returnValue(productDataStore_mock));
			}
		});

		findAgentsProvidingService = new FindAgentsProvidingServiceBehaviour(behaviour_mock, jadePlatform_mock);
	}

	@After
	public void tearDown() {
		context.assertIsSatisfied();
	}

	@Test
	public void action_agentsFound() throws FIPAException {
		final String requiredServiceName = "serviceName";
		final Order order_mock = context.mock(Order.class);
		order_mock.serviceName = requiredServiceName;
		final DFAgentDescription[] agentDescriptions = new DFAgentDescription[] { new DFAgentDescription() };

		context.checking(new Expectations() {
			{
				oneOf(productDataStore_mock).getOrder();
				will(returnValue(order_mock));

				oneOf(jadePlatform_mock).search(with(agent_mock), with(new TypeSafeMatcher<DFAgentDescription>() {

					@Override
					public void describeTo(Description description) {
						description.appendText("serviceDescription template doesn't match");
					}

					@Override
					protected boolean matchesSafely(DFAgentDescription item) {
						ServiceDescription serviceDesription = (ServiceDescription) item.getAllServices().next();
						if (serviceDesription.getName().equalsIgnoreCase(requiredServiceName)) {
							return true;
						}
						return false;
					}
				}));
				will(returnValue(agentDescriptions));

				oneOf(productDataStore_mock)
						.setAgentsProvidingService(with(new TypeSafeMatcher<List<DFAgentDescription>>() {
							@Override
							public void describeTo(Description description) {
								description.appendText("agentDescription doesn't match");
							}

							@Override
							protected boolean matchesSafely(List<DFAgentDescription> item) {
								if (item.get(0) != agentDescriptions[0]) {
									return false;
								}
								return true;
							}
						}));
			}
		});

		findAgentsProvidingService.action();
		Assert.assertEquals(FindAgentsProvidingServiceBehaviour.AgentsFound, findAgentsProvidingService.onEnd());
	}

	@Test
	public void action_agentsNotFound() throws FIPAException {
		final String requiredServiceName = "serviceName";
		final Order order_mock = context.mock(Order.class);
		order_mock.serviceName = requiredServiceName;
		final DFAgentDescription[] agentDescriptions = new DFAgentDescription[] {};

		context.checking(new Expectations() {
			{
				oneOf(productDataStore_mock).getOrder();
				will(returnValue(order_mock));

				oneOf(jadePlatform_mock).search(with(agent_mock), with(new TypeSafeMatcher<DFAgentDescription>() {

					@Override
					public void describeTo(Description description) {
						description.appendText("serviceDescription template doesn't match");
					}

					@Override
					protected boolean matchesSafely(DFAgentDescription item) {
						ServiceDescription serviceDesription = (ServiceDescription) item.getAllServices().next();
						if (serviceDesription.getName().equalsIgnoreCase(requiredServiceName)) {
							return true;
						}
						return false;
					}
				}));
				will(returnValue(agentDescriptions));

				oneOf(productDataStore_mock)
						.setAgentsProvidingService(with(new TypeSafeMatcher<List<DFAgentDescription>>() {
							@Override
							public void describeTo(Description description) {
								description.appendText("agentDescription doesn't match");
							}

							@Override
							protected boolean matchesSafely(List<DFAgentDescription> item) {
								if (item.size() != 0) {
									return false;
								}
								return true;
							}
						}));
			}
		});

		findAgentsProvidingService.action();
		Assert.assertEquals(FindAgentsProvidingServiceBehaviour.AgentsNotFound, findAgentsProvidingService.onEnd());
	}
}
