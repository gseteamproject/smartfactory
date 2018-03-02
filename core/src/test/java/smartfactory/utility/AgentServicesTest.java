package smartfactory.utility;

import java.util.List;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.lib.legacy.ClassImposteriser;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import jade.domain.FIPAAgentManagement.ServiceDescription;
import smartfactory.models.AgentService;

public class AgentServicesTest {

	private final Mockery context = new Mockery() {
		{
			this.setImposteriser(ClassImposteriser.INSTANCE);
		}
	};

	AgentServices testable;

	@Before
	public void setUp() {
		testable = new AgentServices();
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
	public void getServiceDescriptions() {
		final String service_1_name = "service-1";
		final String service_2_name = "service-2";

		AgentService service_1_mock = context.mock(AgentService.class, service_1_name);
		service_1_mock.name = service_1_name;
		AgentService service_2_mock = context.mock(AgentService.class, service_2_name);
		service_2_mock.name = service_2_name;
		ServiceDescription description_1_mock = context.mock(ServiceDescription.class, "description-1");
		ServiceDescription description_2_mock = context.mock(ServiceDescription.class, "description-2");

		context.checking(new Expectations() {
			{
				oneOf(service_1_mock).getServiceDescription();
				will(returnValue(description_1_mock));

				oneOf(service_2_mock).getServiceDescription();
				will(returnValue(description_2_mock));
			}
		});

		testable.addService(service_1_mock);
		testable.addService(service_2_mock);

		List<ServiceDescription> serviceDescriptions = testable.getServiceDescriptions();
		Assert.assertEquals(description_1_mock, serviceDescriptions.get(0));
		Assert.assertEquals(description_2_mock, serviceDescriptions.get(1));
	}
}
