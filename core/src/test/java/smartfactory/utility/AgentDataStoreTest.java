package smartfactory.utility;

import org.jmock.Mockery;
import org.jmock.lib.legacy.ClassImposteriser;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import smartfactory.models.Process;
import smartfactory.models.ProcessOperation;
import smartfactory.models.Product;
import smartfactory.models.Resource;
import smartfactory.platform.AgentPlatform;
import smartfactory.utility.AgentDataStore;

public class AgentDataStoreTest {

	private final Mockery context = new Mockery() {
		{
			this.setImposteriser(ClassImposteriser.INSTANCE);
		}
	};

	AgentDataStore testable;

	@Before
	public void setUp() {
		testable = new AgentDataStore();
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
	public void getMachine() {
		final Resource resource_mock = context.mock(Resource.class);

		testable.put("resource", resource_mock);

		Assert.assertEquals(resource_mock, testable.getResource());
	}

	@Test
	public void setMachine() {
		final Resource resource_mock = context.mock(Resource.class);

		testable.setResource(resource_mock);

		Assert.assertEquals(resource_mock, testable.get("resource"));
	}
}
