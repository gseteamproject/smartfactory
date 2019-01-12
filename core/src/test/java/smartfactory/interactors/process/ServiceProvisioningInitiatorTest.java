package smartfactory.interactors.process;

import java.util.Vector;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.lib.legacy.ClassImposteriser;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import jade.content.onto.basic.Action;
import jade.core.AID;
import jade.domain.FIPANames;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.lang.acl.ACLMessage;
import smartfactory.interactors.process.ServiceProvisioningInitiator;
import smartfactory.models.ProcessOperation;
import smartfactory.ontology.ServiceCompleted;
import smartfactory.ontology.ServiceFailed;
import smartfactory.ontology.ServiceProposal;
import smartfactory.ontology.ServiceRefusal;
import smartfactory.platform.AgentPlatform;
import smartfactory.utility.AgentDataStore;

public class ServiceProvisioningInitiatorTest {

	private final Mockery context = new Mockery() {
		{
			this.setImposteriser(ClassImposteriser.INSTANCE);
		}
	};

	AgentDataStore dataStore_mock;

	ServiceProvisioningInitiator testable;

	@Before
	public void setUp() {
		dataStore_mock = context.mock(AgentDataStore.class);

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
		final ProcessOperation processOperation_mock = context.mock(ProcessOperation.class);
		processOperation_mock.agentDescription = agentDescription;
		final ServiceCompleted serviceCompleted_mock = context.mock(ServiceCompleted.class);
		final AgentPlatform agentPlatform_mock = context.mock(AgentPlatform.class);

		context.checking(new Expectations() {
			{
				oneOf(dataStore_mock).getAgentPlatform();
				will(returnValue(agentPlatform_mock));

				oneOf(agentPlatform_mock).extractContent(message);
				will(returnValue(serviceCompleted_mock));

				oneOf(serviceCompleted_mock).getDurationCompleted();
				will(returnValue(123));

				exactly(2).of(dataStore_mock).getProcessOperation();
				will(returnValue(processOperation_mock));

				oneOf(processOperation_mock).servicePerformedSuccesfully();
			}
		});

		testable.handleInform(message);
	}

	@Test
	public void handleAgree() {
		final ACLMessage message = new ACLMessage(ACLMessage.AGREE);
		final DFAgentDescription agentDescription = new DFAgentDescription();
		agentDescription.setName(new AID("test-agent", AID.ISGUID));
		final ProcessOperation processOperation_mock = context.mock(ProcessOperation.class);
		processOperation_mock.agentDescription = agentDescription;
		final AgentPlatform agentPlatform_mock = context.mock(AgentPlatform.class);
		final ServiceProposal serviceProposal_mock = context.mock(ServiceProposal.class);

		context.checking(new Expectations() {
			{
				oneOf(dataStore_mock).getAgentPlatform();
				will(returnValue(agentPlatform_mock));

				oneOf(agentPlatform_mock).extractContent(message);
				will(returnValue(serviceProposal_mock));

				oneOf(serviceProposal_mock).getDurationEstimated();
				will(returnValue(123));

				oneOf(dataStore_mock).getProcessOperation();
				will(returnValue(processOperation_mock));
			}
		});

		testable.handleAgree(message);
	}

	@Test
	public void handleRefuse() {
		final ACLMessage message = new ACLMessage(ACLMessage.REFUSE);
		final DFAgentDescription agentDescription = new DFAgentDescription();
		agentDescription.setName(new AID("test-agent", AID.ISGUID));
		ProcessOperation processOperation_mock = context.mock(ProcessOperation.class);
		processOperation_mock.agentDescription = agentDescription;
		final AgentPlatform agentPlatform_mock = context.mock(AgentPlatform.class);
		final ServiceRefusal serviceRefuse_mock = context.mock(ServiceRefusal.class);

		context.checking(new Expectations() {
			{
				oneOf(dataStore_mock).getAgentPlatform();
				will(returnValue(agentPlatform_mock));

				oneOf(agentPlatform_mock).extractContent(message);
				will(returnValue(serviceRefuse_mock));

				oneOf(serviceRefuse_mock).getRefusalReason();
				will(returnValue("planned-refusal"));

				exactly(2).of(dataStore_mock).getProcessOperation();
				will(returnValue(processOperation_mock));

				oneOf(processOperation_mock).servicePerformedUnsuccesfully();
			}
		});

		testable.handleRefuse(message);
	}

	@Test
	public void handleFailure() {
		final ACLMessage message = new ACLMessage(ACLMessage.FAILURE);
		final DFAgentDescription agentDescription = new DFAgentDescription();
		agentDescription.setName(new AID("test-agent", AID.ISGUID));
		ProcessOperation processOperation_mock = context.mock(ProcessOperation.class);
		processOperation_mock.agentDescription = agentDescription;
		final AgentPlatform agentPlatform_mock = context.mock(AgentPlatform.class);
		final ServiceFailed serviceFailed_mock = context.mock(ServiceFailed.class);

		context.checking(new Expectations() {
			{
				oneOf(dataStore_mock).getAgentPlatform();
				will(returnValue(agentPlatform_mock));

				oneOf(agentPlatform_mock).extractContent(message);
				will(returnValue(serviceFailed_mock));

				oneOf(serviceFailed_mock).getFailedReason();
				will(returnValue("planned-refusal"));

				exactly(2).of(dataStore_mock).getProcessOperation();
				will(returnValue(processOperation_mock));

				oneOf(processOperation_mock).servicePerformedUnsuccesfully();
			}
		});

		testable.handleFailure(message);
	}

	@Test
	public void prepareRequests() {
		ACLMessage message = null;

		final AID aid = new AID();
		final ProcessOperation processOperation = new ProcessOperation();
		processOperation.agentDescription = new DFAgentDescription();
		processOperation.agentDescription.setName(aid);

		final AgentPlatform agentPlatform_mock = context.mock(AgentPlatform.class);

		context.checking(new Expectations() {
			{
				exactly(2).of(dataStore_mock).getProcessOperation();
				will(returnValue(processOperation));

				oneOf(dataStore_mock).getAgentPlatform();
				will(returnValue(agentPlatform_mock));

				// TODO : add Action matcher
				// TODO : add ACLMessage matcher
				oneOf(agentPlatform_mock).fillContent(with(any(ACLMessage.class)), with(any(Action.class)));
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
		ProcessOperation processOperation_mock = context.mock(ProcessOperation.class);

		context.checking(new Expectations() {
			{
				oneOf(dataStore_mock).getProcessOperation();
				will(returnValue(processOperation_mock));

				oneOf(processOperation_mock).isServicePerformedSuccesfully();
				will(returnValue(servicePerformedResult));
			}
		});

		Assert.assertEquals(servicePerformedResult, testable.next());
	}
}
