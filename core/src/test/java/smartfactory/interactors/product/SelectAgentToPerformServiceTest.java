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
import smartfactory.models.Order;

public class SelectAgentToPerformServiceTest {

	private final Mockery context = new Mockery() {
		{
			this.setImposteriser(ClassImposteriser.INSTANCE);
		}
	};

	ProductDataStore productDataStore_mock;

	SelectAgentToPerformService selectAgentToPerformService;

	@Before
	public void setUp() {
		productDataStore_mock = context.mock(ProductDataStore.class);

		selectAgentToPerformService = new SelectAgentToPerformService(productDataStore_mock);
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
		final Order order_mock = context.mock(Order.class);
		order_mock.agentsDescription = agentsProvidingService;

		context.checking(new Expectations() {
			{
				oneOf(productDataStore_mock).getOrder();
				will(returnValue(order_mock));
				// TODO : add matcher for agentDescription

				oneOf(productDataStore_mock).getOrder();
				will(returnValue(order_mock));
			}
		});

		selectAgentToPerformService.execute();
	}

	@Test
	public void next() {
		final Order order_mock = context.mock(Order.class);
		final int isAgentSelected = Order.AgentSelected;

		context.checking(new Expectations() {
			{
				oneOf(productDataStore_mock).getOrder();
				will(returnValue(order_mock));

				oneOf(order_mock).isAgentSelected();
				will(returnValue(isAgentSelected));
			}
		});

		Assert.assertEquals(isAgentSelected, selectAgentToPerformService.next());
	}
}
