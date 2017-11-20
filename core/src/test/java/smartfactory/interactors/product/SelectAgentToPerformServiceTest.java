package smartfactory.interactors.product;

import java.util.Arrays;
import java.util.List;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.lib.legacy.ClassImposteriser;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import jade.domain.FIPAAgentManagement.DFAgentDescription;
import smartfactory.dataStores.ProductDataStore;
import smartfactory.interactors.product.SelectAgentToPerformService;
import smartfactory.models.ServiceProvisioning;

public class SelectAgentToPerformServiceTest {

	private final Mockery context = new Mockery() {
		{
			this.setImposteriser(ClassImposteriser.INSTANCE);
		}
	};

	ProductDataStore productDataStore_mock;

	SelectAgentToPerformService testable;

	@Before
	public void setUp() {
		productDataStore_mock = context.mock(ProductDataStore.class);

		testable = new SelectAgentToPerformService(productDataStore_mock);
	}

	@After
	public void tearDown() {
		context.assertIsSatisfied();
	}

	@Test
	public void execute() {
		final DFAgentDescription agentProvidingService = new DFAgentDescription();
		final List<DFAgentDescription> agentsProvidingService = Arrays
				.asList(new DFAgentDescription[] { agentProvidingService });
		final ServiceProvisioning order_mock = context.mock(ServiceProvisioning.class);
		order_mock.agentsDescription = agentsProvidingService;

		context.checking(new Expectations() {
			{
				oneOf(productDataStore_mock).getServiceProvisioning();
				will(returnValue(order_mock));
				// TODO : add matcher for agentDescription

				oneOf(productDataStore_mock).getServiceProvisioning();
				will(returnValue(order_mock));
			}
		});

		testable.execute();
	}

	@Test
	public void next() {
		final ServiceProvisioning order_mock = context.mock(ServiceProvisioning.class);
		final int isAgentSelected = ServiceProvisioning.AgentSelected;

		context.checking(new Expectations() {
			{
				oneOf(productDataStore_mock).getServiceProvisioning();
				will(returnValue(order_mock));

				oneOf(order_mock).isAgentSelected();
				will(returnValue(isAgentSelected));
			}
		});

		Assert.assertEquals(isAgentSelected, testable.next());
	}
}
