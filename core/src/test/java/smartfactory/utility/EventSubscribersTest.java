package smartfactory.utility;

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
import jade.proto.SubscriptionResponder.Subscription;
import smartfactory.matchers.ACLMessageMatcher;
import smartfactory.models.Event;

public class EventSubscribersTest {

	private final Mockery context = new Mockery() {
		{
			this.setImposteriser(ClassImposteriser.INSTANCE);
		}
	};

	EventSubscribers testable;

	@Before
	public void setUp() {
		testable = new EventSubscribers();
	}

	@After
	public void tearDown() {
		context.assertIsSatisfied();
	}

	@Test
	public void register() throws RefuseException, NotUnderstoodException {
		Subscription subscription_mock = context.mock(Subscription.class);
		ACLMessage message_mock = context.mock(ACLMessage.class);

		context.checking(new Expectations() {
			{
				oneOf(subscription_mock).getMessage();
				will(returnValue(message_mock));

				oneOf(message_mock).getConversationId();
				will(returnValue("conversation"));
			}
		});

		Assert.assertTrue(testable.register(subscription_mock));
	}

	@Test
	public void deregister() throws FailureException {
		Subscription subscription_mock = context.mock(Subscription.class);
		ACLMessage message_mock = context.mock(ACLMessage.class);

		context.checking(new Expectations() {
			{
				oneOf(subscription_mock).getMessage();
				will(returnValue(message_mock));

				oneOf(message_mock).getConversationId();
				will(returnValue("conversation"));
			}
		});

		Assert.assertTrue(testable.deregister(subscription_mock));
	}

	@Test
	public void notifyAll_operation_success() throws RefuseException, NotUnderstoodException {
		Subscription subscription_mock = context.mock(Subscription.class);
		ACLMessage message_mock = context.mock(ACLMessage.class);

		context.checking(new Expectations() {
			{
				oneOf(subscription_mock).getMessage();
				will(returnValue(message_mock));

				oneOf(message_mock).getConversationId();
				will(returnValue("self-messaging"));

				oneOf(subscription_mock).notify(with(new ACLMessageMatcher().expectPerformative(ACLMessage.INFORM)
						.expectContent(Event.OPERATION_COMPLETED_SUCCESS)));
			}
		});

		testable.register(subscription_mock);

		testable.notifyAll(Event.OPERATION_COMPLETED_SUCCESS);
	}

	@Test
	public void notifyAll_operation_failure() throws RefuseException, NotUnderstoodException {
		Subscription subscription_mock = context.mock(Subscription.class);
		ACLMessage message_mock = context.mock(ACLMessage.class);

		context.checking(new Expectations() {
			{
				oneOf(subscription_mock).getMessage();
				will(returnValue(message_mock));

				oneOf(message_mock).getConversationId();
				will(returnValue("self-messaging"));

				oneOf(subscription_mock).notify(with(new ACLMessageMatcher().expectPerformative(ACLMessage.INFORM)
						.expectContent(Event.OPERATION_COMPLETED_FAILURE)));
			}
		});

		testable.register(subscription_mock);

		testable.notifyAll(Event.OPERATION_COMPLETED_FAILURE);
	}

	@Test
	public void notifyAll_process_success() throws RefuseException, NotUnderstoodException {
		Subscription subscription_mock = context.mock(Subscription.class);
		ACLMessage message_mock = context.mock(ACLMessage.class);

		context.checking(new Expectations() {
			{
				oneOf(subscription_mock).getMessage();
				will(returnValue(message_mock));

				oneOf(message_mock).getConversationId();
				will(returnValue("process-status"));

				oneOf(subscription_mock).notify(with(new ACLMessageMatcher().expectPerformative(ACLMessage.INFORM)
						.expectContent(Event.PROCESS_COMPLETED_SUCCESS)));
			}
		});

		testable.register(subscription_mock);

		testable.notifyAll(Event.PROCESS_COMPLETED_SUCCESS);
	}

	@Test
	public void notifyAll_process_failure() throws RefuseException, NotUnderstoodException {
		Subscription subscription_mock = context.mock(Subscription.class);
		ACLMessage message_mock = context.mock(ACLMessage.class);

		context.checking(new Expectations() {
			{
				oneOf(subscription_mock).getMessage();
				will(returnValue(message_mock));

				oneOf(message_mock).getConversationId();
				will(returnValue("process-status"));

				oneOf(subscription_mock).notify(with(new ACLMessageMatcher().expectPerformative(ACLMessage.INFORM)
						.expectContent(Event.PROCESS_COMPLETED_FAILURE)));
			}
		});

		testable.register(subscription_mock);

		testable.notifyAll(Event.PROCESS_COMPLETED_FAILURE);
	}

	@Test
	public void notifyAll_event() throws RefuseException, NotUnderstoodException {
		Subscription subscription_mock = context.mock(Subscription.class);
		ACLMessage message_mock = context.mock(ACLMessage.class);

		context.checking(new Expectations() {
			{
				oneOf(subscription_mock).getMessage();
				will(returnValue(message_mock));

				oneOf(message_mock).getConversationId();
				will(returnValue("event"));

				oneOf(subscription_mock).notify(
						with(new ACLMessageMatcher().expectPerformative(ACLMessage.INFORM).expectContent("event")));
			}
		});

		testable.register(subscription_mock);

		testable.notifyAll("event");
	}
}
