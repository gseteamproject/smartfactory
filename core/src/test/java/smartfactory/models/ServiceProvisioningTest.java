package smartfactory.models;

import java.util.List;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.lib.legacy.ClassImposteriser;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import jade.domain.FIPAAgentManagement.DFAgentDescription;

public class ServiceProvisioningTest {

	private final Mockery context = new Mockery() {
		{
			this.setImposteriser(ClassImposteriser.INSTANCE);
		}
	};

	ServiceProvisioning testable;

	@Before
	public void setUp() {
		testable = new ServiceProvisioning();
	}

	@After
	public void tearDown() {
		context.assertIsSatisfied();
	}

	@SuppressWarnings("unchecked")
	@Test
	public void servicePerformedUnsuccesfully() {
		final DFAgentDescription agentDescription_mock = context.mock(DFAgentDescription.class);
		final List<DFAgentDescription> agentDescriptions_mock = context.mock(List.class);

		testable.agentDescription = agentDescription_mock;
		testable.agentsDescription = agentDescriptions_mock;

		context.checking(new Expectations() {
			{
				oneOf(agentDescriptions_mock).remove(agentDescription_mock);
			}
		});

		testable.servicePerformedUnsuccesfully();

		Assert.assertEquals(ServiceProvisioning.ServicePerformedUnSuccessfully,
				testable.isServicePerformedSuccesfully());
	}

	@Test
	public void servicePerformedSuccesfully() {
		testable.servicePerformedSuccesfully();

		Assert.assertEquals(ServiceProvisioning.ServicePerformedSuccessfully, testable.isServicePerformedSuccesfully());
	}
}
