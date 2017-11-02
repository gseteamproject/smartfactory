package smartfactory.interactors.product;

import java.util.List;
import java.util.Vector;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.lib.legacy.ClassImposteriser;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import jade.core.AID;
import jade.domain.FIPANames;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.lang.acl.ACLMessage;
import smartfactory.dataStores.ProductDataStore;
import smartfactory.interactors.product.PerformServiceInitiator;
import smartfactory.models.Order;

public class PerformServiceInitiatorTest {

	private final Mockery context = new Mockery() {
		{
			this.setImposteriser(ClassImposteriser.INSTANCE);
		}
	};

	ProductDataStore productDataStore_mock;

	PerformServiceInitiator askSelectedAgentToPerformService;

	@Before
	public void setUp() {
		productDataStore_mock = context.mock(ProductDataStore.class);

		askSelectedAgentToPerformService = new PerformServiceInitiator(productDataStore_mock);
	}

	@After
	public void tearDown() {
		context.assertIsSatisfied();
	}

	@Test
	public void removeAgentProvidingService() {
		@SuppressWarnings("unchecked")
		List<DFAgentDescription> agentsDescription_mock = context.mock(List.class);
		final DFAgentDescription agentDescription = new DFAgentDescription();
		final Order order = new Order();
		order.agentDescription = agentDescription;
		order.agentsDescription = agentsDescription_mock;

		context.checking(new Expectations() {
			{
				exactly(2).of(productDataStore_mock).getOrder();
				will(returnValue(order));

				oneOf(agentsDescription_mock).remove(agentDescription);
			}
		});

		askSelectedAgentToPerformService.removeAgentProvidingService();
	}

	@Test
	public void handleInform() {
		final ACLMessage message = new ACLMessage(ACLMessage.INFORM);
		final Order order = new Order();
		order.agentDescription = new DFAgentDescription();

		context.checking(new Expectations() {
			{
				oneOf(productDataStore_mock).getOrder();
				will(returnValue(order));
			}
		});

		askSelectedAgentToPerformService.handleInform(message);
	}

	@Test
	public void handleAgree() {
		final ACLMessage message = new ACLMessage(ACLMessage.AGREE);
		final Order order = new Order();
		order.agentDescription = new DFAgentDescription();

		context.checking(new Expectations() {
			{
				oneOf(productDataStore_mock).getOrder();
				will(returnValue(order));
			}
		});

		askSelectedAgentToPerformService.handleAgree(message);
	}

	@Test
	public void handleRefuse() {
		@SuppressWarnings("unchecked")
		List<DFAgentDescription> agentsDescription_mock = context.mock(List.class);
		final DFAgentDescription agentDescription = new DFAgentDescription();
		final Order order = new Order();
		order.agentDescription = agentDescription;
		order.agentsDescription = agentsDescription_mock;
		final ACLMessage message = new ACLMessage(ACLMessage.REFUSE);

		context.checking(new Expectations() {
			{
				exactly(3).of(productDataStore_mock).getOrder();
				will(returnValue(order));

				oneOf(agentsDescription_mock).remove(agentDescription);
			}
		});

		askSelectedAgentToPerformService.handleRefuse(message);
	}

	@Test
	public void handleFailure() {
		@SuppressWarnings("unchecked")
		List<DFAgentDescription> agentsDescription_mock = context.mock(List.class);
		final DFAgentDescription agentDescription = new DFAgentDescription();
		final Order order = new Order();
		order.agentDescription = agentDescription;
		order.agentsDescription = agentsDescription_mock;
		final ACLMessage message = new ACLMessage(ACLMessage.FAILURE);

		context.checking(new Expectations() {
			{
				exactly(3).of(productDataStore_mock).getOrder();
				will(returnValue(order));

				oneOf(agentsDescription_mock).remove(agentDescription);
			}
		});

		askSelectedAgentToPerformService.handleFailure(message);
	}

	@Test
	public void prepareRequests() {
		ACLMessage message = null;

		final AID aid = new AID();
		final Order order = new Order();
		order.agentDescription = new DFAgentDescription();
		order.agentDescription.setName(aid);

		context.checking(new Expectations() {
			{
				oneOf(productDataStore_mock).getOrder();
				will(returnValue(order));
			}
		});

		Vector<ACLMessage> messages = askSelectedAgentToPerformService.prepareRequests(message);
		Assert.assertEquals(1, messages.size());
		ACLMessage request = messages.get(0);
		Assert.assertEquals(ACLMessage.REQUEST, request.getPerformative());
		Assert.assertEquals(aid, request.getAllReceiver().next());
		Assert.assertEquals(FIPANames.InteractionProtocol.FIPA_REQUEST, request.getProtocol());
	}

	@Test
	public void next_ServicePerformedUnSuccessfully() {
		Assert.assertEquals(PerformServiceInitiator.ServicePerformedUnSuccessfully,
				askSelectedAgentToPerformService.next());
	}
}
