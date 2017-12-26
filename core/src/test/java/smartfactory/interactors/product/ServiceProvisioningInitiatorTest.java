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
import smartfactory.interactors.product.ServiceProvisioningInitiator;
import smartfactory.models.ServiceProvisioning;

public class ServiceProvisioningInitiatorTest {

	private final Mockery context = new Mockery() {
		{
			this.setImposteriser(ClassImposteriser.INSTANCE);
		}
	};

	ProductDataStore dataStore_mock;

	ServiceProvisioningInitiator testable;

	@Before
	public void setUp() {
		dataStore_mock = context.mock(ProductDataStore.class);

		testable = new ServiceProvisioningInitiator(dataStore_mock);
	}

	@After
	public void tearDown() {
		context.assertIsSatisfied();
	}

	@Test
	public void handleInform() {
		final ACLMessage message = new ACLMessage(ACLMessage.INFORM);
		final DFAgentDescription agentDescription = new DFAgentDescription();
		agentDescription.setName(new AID("test-agent", AID.ISGUID));
		final ServiceProvisioning serviceProvisioning_mock = context.mock(ServiceProvisioning.class);
		serviceProvisioning_mock.agentDescription = agentDescription;

		context.checking(new Expectations() {
			{
				exactly(2).of(dataStore_mock).getServiceProvisioning();
				will(returnValue(serviceProvisioning_mock));

				oneOf(serviceProvisioning_mock).servicePerformedSuccesfully();
			}
		});

		testable.handleInform(message);
	}

	@Test
	public void handleAgree() {
		final ACLMessage message = new ACLMessage(ACLMessage.AGREE);
		final DFAgentDescription agentDescription = new DFAgentDescription();
		agentDescription.setName(new AID("test-agent", AID.ISGUID));
		final ServiceProvisioning serviceProvisioning_mock = context.mock(ServiceProvisioning.class);
		serviceProvisioning_mock.agentDescription = agentDescription;

		context.checking(new Expectations() {
			{
				oneOf(dataStore_mock).getServiceProvisioning();
				will(returnValue(serviceProvisioning_mock));
			}
		});

		testable.handleAgree(message);
	}

	@Test
	public void handleRefuse() {
		final ACLMessage message = new ACLMessage(ACLMessage.REFUSE);
		final DFAgentDescription agentDescription = new DFAgentDescription();
		agentDescription.setName(new AID("test-agent", AID.ISGUID));
		ServiceProvisioning serviceProvisioning_mock = context.mock(ServiceProvisioning.class);
		serviceProvisioning_mock.agentDescription = agentDescription;

		context.checking(new Expectations() {
			{
				exactly(2).of(dataStore_mock).getServiceProvisioning();
				will(returnValue(serviceProvisioning_mock));

				oneOf(serviceProvisioning_mock).servicePerformedUnsuccesfully();
			}
		});

		testable.handleRefuse(message);
	}

	@Test
	public void handleFailure() {
		final ACLMessage message = new ACLMessage(ACLMessage.FAILURE);
		final DFAgentDescription agentDescription = new DFAgentDescription();
		agentDescription.setName(new AID("test-agent", AID.ISGUID));
		ServiceProvisioning serviceProvisioning_mock = context.mock(ServiceProvisioning.class);
		serviceProvisioning_mock.agentDescription = agentDescription;

		context.checking(new Expectations() {
			{
				exactly(2).of(dataStore_mock).getServiceProvisioning();
				will(returnValue(serviceProvisioning_mock));

				oneOf(serviceProvisioning_mock).servicePerformedUnsuccesfully();
			}
		});

		testable.handleFailure(message);
	}

	@Test
	public void prepareRequests() {
		ACLMessage message = null;

		final AID aid = new AID();
		final ServiceProvisioning serviceProvisioning = new ServiceProvisioning();
		serviceProvisioning.agentDescription = new DFAgentDescription();
		serviceProvisioning.agentDescription.setName(aid);

		context.checking(new Expectations() {
			{
				exactly(2).of(dataStore_mock).getServiceProvisioning();
				will(returnValue(serviceProvisioning));
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
	public void next() {
		final int servicePerformedResult = 123;
		ServiceProvisioning serviceProvisioning_mock = context.mock(ServiceProvisioning.class);

		context.checking(new Expectations() {
			{
				oneOf(dataStore_mock).getServiceProvisioning();
				will(returnValue(serviceProvisioning_mock));

				oneOf(serviceProvisioning_mock).isServicePerformedSuccesfully();
				will(returnValue(servicePerformedResult));
			}
		});

		Assert.assertEquals(servicePerformedResult, testable.next());
	}
}
