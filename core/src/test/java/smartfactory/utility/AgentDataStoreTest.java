package smartfactory.utility;

import org.jmock.Mockery;
import org.jmock.lib.legacy.ClassImposteriser;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import jade.core.Agent;
import jade.lang.acl.ACLMessage;
import smartfactory.configuration.AgentConfiguration;
import smartfactory.eventSubscription.EventSubscribers;
import smartfactory.models.Process;
import smartfactory.models.ProcessOperation;
import smartfactory.models.Product;
import smartfactory.models.Resource;
import smartfactory.platform.AgentPlatform;

public class AgentDataStoreTest {

	private final Mockery context = new Mockery() {
		{
			this.setImposteriser(ClassImposteriser.INSTANCE);
		}
	};

	AgentDataStore testable;

	Agent agent_mock;

	@Before
	public void setUp() {
		agent_mock = context.mock(Agent.class);

		testable = new AgentDataStore(agent_mock);
	}

	@After
	public void tearDown() {
		context.assertIsSatisfied();
	}

	@Test
	public void getAgentPlatform() {
		final AgentPlatform agentPlatform_mock = context.mock(AgentPlatform.class);

		testable.put("agentPlatform", agentPlatform_mock);

		Assert.assertEquals(agentPlatform_mock, testable.getAgentPlatform());
	}

	@Test
	public void setAgentPlatform() {
		final AgentPlatform agentPlatform_mock = context.mock(AgentPlatform.class);

		testable.setAgentPlatform(agentPlatform_mock);

		Assert.assertEquals(agentPlatform_mock, testable.get("agentPlatform"));
	}

	@Test
	public void getProcess() {
		final Process mock = context.mock(Process.class);

		testable.put("process", mock);

		Assert.assertEquals(mock, testable.getProcess());
	}

	@Test
	public void setProcess() {
		final Process mock = context.mock(Process.class);

		testable.setProcess(mock);

		Assert.assertEquals(mock, testable.get("process"));
	}

	@Test
	public void getProcessOperation() {
		final ProcessOperation mock = context.mock(ProcessOperation.class);

		testable.put("process-operation", mock);

		Assert.assertEquals(mock, testable.getProcessOperation());
	}

	@Test
	public void setProcessOperation() {
		final ProcessOperation mock = context.mock(ProcessOperation.class);

		testable.setProcessOperation(mock);

		Assert.assertEquals(mock, testable.get("process-operation"));
	}

	@Test
	public void getProduct() {
		final Product mock = context.mock(Product.class);

		testable.put("product", mock);

		Assert.assertEquals(mock, testable.getProduct());
	}

	@Test
	public void setProduct() {
		final Product mock = context.mock(Product.class);

		testable.setProduct(mock);

		Assert.assertEquals(mock, testable.get("product"));
	}

	@Test
	public void getResource() {
		final Resource mock = context.mock(Resource.class);

		testable.put("resource", mock);

		Assert.assertEquals(mock, testable.getResource());
	}

	@Test
	public void setResource() {
		final Resource mock = context.mock(Resource.class);

		testable.setResource(mock);

		Assert.assertEquals(mock, testable.get("resource"));
	}

	@Test
	public void getAgent() {
		final Agent mock = context.mock(Agent.class, "agent_mock");

		testable.put("agent", mock);

		Assert.assertEquals(mock, testable.getAgent());
	}

	@Test
	public void setAgent() {
		final Agent mock = context.mock(Agent.class, "agent_mock");

		testable.setAgent(mock);

		Assert.assertEquals(mock, testable.get("agent"));
	}

	@Test
	public void getEventSubscribers() {
		final EventSubscribers mock = context.mock(EventSubscribers.class);

		testable.put("eventSubscribers", mock);

		Assert.assertEquals(mock, testable.getEventSubsribers());
	}

	@Test
	public void setEventSubscribers() {
		final EventSubscribers mock = context.mock(EventSubscribers.class);

		testable.setEventSubscribers(mock);

		Assert.assertEquals(mock, testable.get("eventSubscribers"));
	}

	@Test
	public void getAgentServices() {
		final AgentServices mock = context.mock(AgentServices.class);

		testable.put("agentServices", mock);

		Assert.assertEquals(mock, testable.getAgentServices());
	}

	@Test
	public void setAgentServices() {
		final AgentServices mock = context.mock(AgentServices.class);

		testable.setAgentServices(mock);

		Assert.assertEquals(mock, testable.get("agentServices"));
	}

	@Test
	public void getSubAgentConfiguration() {
		final AgentConfiguration mock = context.mock(AgentConfiguration.class);

		testable.put("subAgentConfiguration", mock);

		Assert.assertEquals(mock, testable.getSubAgentConfiguration());
	}

	@Test
	public void setSubAgentConfiguration() {
		final AgentConfiguration mock = context.mock(AgentConfiguration.class);

		testable.setSubAgentConfiguration(mock);

		Assert.assertEquals(mock, testable.get("subAgentConfiguration"));
	}

	@Test
	public void getActivityResult() {
		final ACLMessage mock = context.mock(ACLMessage.class);

		testable.put("activity-result", mock);

		Assert.assertEquals(mock, testable.getActivityResult());
	}

	@Test
	public void setActivityResult() {
		final ACLMessage mock = context.mock(ACLMessage.class);

		testable.setActivityResult(mock);

		Assert.assertEquals(mock, testable.get("activity-result"));
	}

	@Test
	public void getActivityRequest() {
		final ACLMessage mock = context.mock(ACLMessage.class);

		testable.put("activity-request", mock);

		Assert.assertEquals(mock, testable.getActivityRequest());
	}

	@Test
	public void setActivityRequest() {
		final ACLMessage mock = context.mock(ACLMessage.class);

		testable.setActivityRequest(mock);

		Assert.assertEquals(mock, testable.get("activity-request"));
	}
}
