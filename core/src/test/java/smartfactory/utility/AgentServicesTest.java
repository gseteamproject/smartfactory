package smartfactory.utility;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.lib.legacy.ClassImposteriser;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import jade.core.Agent;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import smartfactory.matchers.DFAgentDescriptionMatcher;
import smartfactory.models.AgentService;
import smartfactory.platform.AgentPlatform;

public class AgentServicesTest {

	private final Mockery context = new Mockery() {
		{
			this.setImposteriser(ClassImposteriser.INSTANCE);
		}
	};

	AgentServices testable;

	AgentDataStore agentDataStore_mock;

	@Before
	public void setUp() {
		agentDataStore_mock = context.mock(AgentDataStore.class);

		testable = new AgentServices(agentDataStore_mock);
	}

	@After
	public void tearDown() {
		context.assertIsSatisfied();
	}

	@Test
	public void addService() {
		AgentService service_mock = context.mock(AgentService.class);

		testable.addService(service_mock);
	}

	@Test
	public void registerAgentServices() {
		final AgentService service_1_mock = context.mock(AgentService.class, "service_1");
		final AgentService service_2_mock = context.mock(AgentService.class, "service_2");
		final String service_1_name = "service-1";
		final String service_2_name = "service-2";
		final ServiceDescription description_1_mock = context.mock(ServiceDescription.class, "description_1");
		final ServiceDescription description_2_mock = context.mock(ServiceDescription.class, "description_2");
		final Agent agent_mock = context.mock(Agent.class);
		final AgentPlatform agentPlatform_mock = context.mock(AgentPlatform.class);

		service_1_mock.name = service_1_name;
		service_2_mock.name = service_2_name;
		testable.addService(service_1_mock);
		testable.addService(service_2_mock);

		context.checking(new Expectations() {
			{
				oneOf(service_1_mock).getServiceDescription();
				will(returnValue(description_1_mock));

				oneOf(service_2_mock).getServiceDescription();
				will(returnValue(description_2_mock));

				oneOf(agentDataStore_mock).getAgent();
				will(returnValue(agent_mock));

				oneOf(agentDataStore_mock).getAgentPlatform();
				will(returnValue(agentPlatform_mock));

				oneOf(agentPlatform_mock).registerAgentServices(with(new DFAgentDescriptionMatcher().expectName(null)
						.expectServices(new ServiceDescription[] { description_1_mock, description_2_mock })));
			}
		});

		testable.registerAgentServices();
	}

	@Test
	public void deregisterAgentServices() {
		final AgentPlatform agentPlatform_mock = context.mock(AgentPlatform.class);

		context.checking(new Expectations() {
			{
				oneOf(agentDataStore_mock).getAgentPlatform();
				will(returnValue(agentPlatform_mock));

				oneOf(agentPlatform_mock).deregisterAgentServices();
			}
		});

		testable.deregisterAgentServices();
	}
}
