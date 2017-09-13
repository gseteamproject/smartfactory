package smartfactory.behaviours;

import java.util.List;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.lib.legacy.ClassImposteriser;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import jade.domain.FIPAException;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import smartfactory.dataStores.ProductDataStore;
import smartfactory.matchers.DFAgentDescriptionMatcher;
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

	FindAgentsProvidingService findAgentsProvidingService;

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

		findAgentsProvidingService = new FindAgentsProvidingService(behaviour_mock, jadePlatform_mock);
	}

	@After
	public void tearDown() {
		context.assertIsSatisfied();
	}

	@SuppressWarnings("unchecked")
	@Test
	public void action() throws FIPAException {
		final String requiredServiceName = "serviceName";
		final DFAgentDescription[] agentDescriptions = new DFAgentDescription[0];

		context.checking(new Expectations() {
			{
				oneOf(productDataStore_mock).getRequiredServiceName();
				will(returnValue(requiredServiceName));

				// TODO : add Matcher inside Matcher
				// TODO : check for ServiceDescription
				oneOf(jadePlatform_mock).search(with(agent_mock), with(new DFAgentDescriptionMatcher()));
				will(returnValue(agentDescriptions));

				oneOf(productDataStore_mock).setAgentsProvidingService(with(any(List.class)));
			}
		});

		findAgentsProvidingService.action();
	}
}
