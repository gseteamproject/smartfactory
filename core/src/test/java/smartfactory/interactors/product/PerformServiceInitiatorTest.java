package smartfactory.interactors.product;

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
import smartfactory.models.ServiceProvisioning;

public class PerformServiceInitiatorTest {

	private final Mockery context = new Mockery() {
		{
			this.setImposteriser(ClassImposteriser.INSTANCE);
		}
	};

	ProductDataStore productDataStore_mock;

	PerformServiceInitiator testable;

	@Before
	public void setUp() {
		productDataStore_mock = context.mock(ProductDataStore.class);

		testable = new PerformServiceInitiator(productDataStore_mock);
	}

	@After
	public void tearDown() {
		context.assertIsSatisfied();
	}

	@Test
	public void handleInform() {
		final ACLMessage message = new ACLMessage(ACLMessage.INFORM);
		final ServiceProvisioning order = new ServiceProvisioning();
		order.agentDescription = new DFAgentDescription();

		context.checking(new Expectations() {
			{
				oneOf(productDataStore_mock).getOrder();
				will(returnValue(order));
			}
		});

		testable.handleInform(message);
	}

	@Test
	public void handleAgree() {
		final ACLMessage message = new ACLMessage(ACLMessage.AGREE);
		final ServiceProvisioning order = new ServiceProvisioning();
		order.agentDescription = new DFAgentDescription();

		context.checking(new Expectations() {
			{
				oneOf(productDataStore_mock).getOrder();
				will(returnValue(order));
			}
		});

		testable.handleAgree(message);
	}

	@Test
	public void handleRefuse() {
		final ACLMessage message = new ACLMessage(ACLMessage.REFUSE);
		final DFAgentDescription agentDescription = new DFAgentDescription();
		ServiceProvisioning order_mock = context.mock(ServiceProvisioning.class);
		order_mock.agentDescription = agentDescription;

		context.checking(new Expectations() {
			{
				exactly(2).of(productDataStore_mock).getOrder();
				will(returnValue(order_mock));

				oneOf(order_mock).removeSelectedAgentFromAgentsList();
			}
		});

		testable.handleRefuse(message);
	}

	@Test
	public void handleFailure() {
		final ACLMessage message = new ACLMessage(ACLMessage.FAILURE);
		final DFAgentDescription agentDescription = new DFAgentDescription();
		ServiceProvisioning order_mock = context.mock(ServiceProvisioning.class);
		order_mock.agentDescription = agentDescription;

		context.checking(new Expectations() {
			{
				exactly(2).of(productDataStore_mock).getOrder();
				will(returnValue(order_mock));

				oneOf(order_mock).removeSelectedAgentFromAgentsList();
			}
		});

		testable.handleFailure(message);
	}

	@Test
	public void prepareRequests() {
		ACLMessage message = null;

		final AID aid = new AID();
		final ServiceProvisioning order = new ServiceProvisioning();
		order.agentDescription = new DFAgentDescription();
		order.agentDescription.setName(aid);

		context.checking(new Expectations() {
			{
				oneOf(productDataStore_mock).getOrder();
				will(returnValue(order));
			}
		});

		Vector<ACLMessage> messages = testable.prepareRequests(message);
		Assert.assertEquals(1, messages.size());
		ACLMessage request = messages.get(0);
		Assert.assertEquals(ACLMessage.REQUEST, request.getPerformative());
		Assert.assertEquals(aid, request.getAllReceiver().next());
		Assert.assertEquals(FIPANames.InteractionProtocol.FIPA_REQUEST, request.getProtocol());
	}

	@Test
	public void next_ServicePerformedUnSuccessfully() {
		Assert.assertEquals(PerformServiceInitiator.ServicePerformedUnSuccessfully, testable.next());
	}
}
