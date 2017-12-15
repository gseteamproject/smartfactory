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

import jade.core.AID;
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

	ProductDataStore dataStore_mock;

	SelectAgentToPerformService testable;

	@Before
	public void setUp() {
		dataStore_mock = context.mock(ProductDataStore.class);

		testable = new SelectAgentToPerformService(dataStore_mock);
	}

	@After
	public void tearDown() {
		context.assertIsSatisfied();
	}

	@Test
	public void execute() {
		final DFAgentDescription agentDescription = new DFAgentDescription();
		agentDescription.setName(new AID("test-agent", AID.ISGUID));
		final List<DFAgentDescription> agentsProvidingService = Arrays
				.asList(new DFAgentDescription[] { agentDescription });
		final ServiceProvisioning serviceProvisioning_mock = context.mock(ServiceProvisioning.class);
		serviceProvisioning_mock.agentsDescription = agentsProvidingService;

		context.checking(new Expectations() {
			{
				exactly(2).of(dataStore_mock).getServiceProvisioning();
				will(returnValue(serviceProvisioning_mock));
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
				oneOf(dataStore_mock).getServiceProvisioning();
				will(returnValue(order_mock));

				oneOf(order_mock).isAgentSelected();
				will(returnValue(isAgentSelected));
			}
		});

		Assert.assertEquals(isAgentSelected, testable.next());
	}
}
