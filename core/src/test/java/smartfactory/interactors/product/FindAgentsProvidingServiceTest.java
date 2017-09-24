package smartfactory.interactors.product;

import java.util.ArrayList;
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

	ProductDataStore productDataStore_mock;

	FindAgentsProvidingService findAgentsProvidingService;

	@Before
	public void setUp() {
		productDataStore_mock = context.mock(ProductDataStore.class);

		findAgentsProvidingService = new FindAgentsProvidingService(productDataStore_mock);
	}

	@After
	public void tearDown() {
		context.assertIsSatisfied();
	}

	@Test
	public void execute() {
		final String requiredServiceName = "serviceName";
		final Order order_mock = context.mock(Order.class);
		order_mock.serviceName = requiredServiceName;
		final List<DFAgentDescription> agentDescriptions = new ArrayList<DFAgentDescription>();
		agentDescriptions.add(new DFAgentDescription());
		final AgentPlatform jadePlatform_mock = context.mock(AgentPlatform.class);

		context.checking(new Expectations() {
			{
				oneOf(productDataStore_mock).getOrder();
				will(returnValue(order_mock));

				oneOf(productDataStore_mock).getAgentPlatform();
				will(returnValue(jadePlatform_mock));

				oneOf(jadePlatform_mock).search(with(new TypeSafeMatcher<DFAgentDescription>() {

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

				oneOf(productDataStore_mock).getOrder();
				will(returnValue(order_mock));

				/*
				 * // TODO add matcher for Order .setAgentsProvidingService(with(new
				 * TypeSafeMatcher<List<DFAgentDescription>>() {
				 * 
				 * @Override public void describeTo(Description description) {
				 * description.appendText("agentDescription doesn't match"); }
				 * 
				 * @Override protected boolean matchesSafely(List<DFAgentDescription> item) { if
				 * (item.get(0) != agentDescriptions[0]) { return false; } return true; } }));
				 */
			}
		});

		findAgentsProvidingService.execute();
	}

	@Test
	public void next() {
		final Order order_mock = context.mock(Order.class);
		final int isAgentsFound = Order.AgentsFound;

		context.checking(new Expectations() {
			{
				oneOf(productDataStore_mock).getOrder();
				will(returnValue(order_mock));

				oneOf(order_mock).isAgentsFound();
				will(returnValue(isAgentsFound));
			}
		});

		Assert.assertEquals(isAgentsFound, findAgentsProvidingService.next());
	}
}
