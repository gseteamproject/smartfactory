package smartfactory.interactors.machine;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.lib.legacy.ClassImposteriser;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import jade.domain.FIPAAgentManagement.FailureException;
import jade.domain.FIPAAgentManagement.NotUnderstoodException;
import jade.domain.FIPAAgentManagement.RefuseException;
import jade.lang.acl.ACLMessage;
import smartfactory.dataStores.MachineDataStore;

public class PerformServiceResponderTest {
	private final Mockery context = new Mockery() {
		{
			this.setImposteriser(ClassImposteriser.INSTANCE);
		}
	};

	MachineDataStore machineDataStore_mock;

	PerformServiceResponder testable;

	@Before
	public void setUp() {
		machineDataStore_mock = context.mock(MachineDataStore.class);

		testable = new PerformServiceResponder(machineDataStore_mock);
	}

	@After
	public void tearDown() {
		context.assertIsSatisfied();
	}

	@Test
	public void handleRequest() throws NotUnderstoodException, RefuseException {
		ACLMessage message_mock = context.mock(ACLMessage.class);
		final ACLMessage reply = new ACLMessage(ACLMessage.NOT_UNDERSTOOD);

		context.checking(new Expectations() {
			{
				oneOf(message_mock).createReply();
				will(returnValue(reply));
			}
		});

		testable.handleRequest(message_mock);
		Assert.assertEquals(ACLMessage.AGREE, reply.getPerformative());
	}

	@Test
	public void prepareResultNotification() throws FailureException {
		ACLMessage message_mock = context.mock(ACLMessage.class);
		final ACLMessage reply = new ACLMessage(ACLMessage.NOT_UNDERSTOOD);

		context.checking(new Expectations() {
			{
				oneOf(message_mock).createReply();
				will(returnValue(reply));
			}
		});

		testable.prepareResultNotification(message_mock, null);
		Assert.assertEquals(ACLMessage.INFORM, reply.getPerformative());
	}
}
