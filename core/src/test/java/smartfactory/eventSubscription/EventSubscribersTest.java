package smartfactory.eventSubscription;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.lib.legacy.ClassImposteriser;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import jade.domain.FIPANames;
import jade.domain.FIPAAgentManagement.FailureException;
import jade.domain.FIPAAgentManagement.NotUnderstoodException;
import jade.domain.FIPAAgentManagement.RefuseException;
import jade.lang.acl.ACLMessage;
import jade.proto.SubscriptionResponder.Subscription;
import smartfactory.eventSubscription.ontology.Event;
import smartfactory.eventSubscription.ontology.EventSubscriptionOntology;
import smartfactory.matchers.ACLMessageMatcher;
import smartfactory.platform.AgentPlatform;
import smartfactory.utility.AgentDataStore;

public class EventSubscribersTest {

	private final Mockery context = new Mockery() {
		{
			this.setImposteriser(ClassImposteriser.INSTANCE);
		}
	};

	EventSubscribers testable;

	AgentDataStore dataStore_mock;

	@Before
	public void setUp() {
		dataStore_mock = context.mock(AgentDataStore.class);

		testable = new EventSubscribers(dataStore_mock);
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
		final Subscription subscription_mock = context.mock(Subscription.class);
		final ACLMessage message_mock = context.mock(ACLMessage.class);
		final AgentPlatform agentPlatform_mock = context.mock(AgentPlatform.class);
		final Event event = new Event();
		event.setId(Event.OPERATION_COMPLETED_SUCCESS);

		context.checking(new Expectations() {
			{
				oneOf(subscription_mock).getMessage();
				will(returnValue(message_mock));

				oneOf(message_mock).getConversationId();
				will(returnValue("self-messaging"));

				oneOf(dataStore_mock).getAgentPlatform();
				will(returnValue(agentPlatform_mock));

				oneOf(agentPlatform_mock).fillContent(with(any(ACLMessage.class)), with(event));

				oneOf(subscription_mock).notify(with(new ACLMessageMatcher().expectPerformative(ACLMessage.INFORM)
						.expectLanguage(FIPANames.ContentLanguage.FIPA_SL)
						.expectOntology(EventSubscriptionOntology.ONTOLOGY_NAME)));
			}
		});

		testable.register(subscription_mock);

		testable.notifyAll(event);
	}

	@Test
	public void notifyAll_operation_failure() throws RefuseException, NotUnderstoodException {
		final Subscription subscription_mock = context.mock(Subscription.class);
		final ACLMessage message_mock = context.mock(ACLMessage.class);
		final AgentPlatform agentPlatform_mock = context.mock(AgentPlatform.class);
		final Event event = new Event();
		event.setId(Event.OPERATION_COMPLETED_FAILURE);

		context.checking(new Expectations() {
			{
				oneOf(subscription_mock).getMessage();
				will(returnValue(message_mock));

				oneOf(message_mock).getConversationId();
				will(returnValue("self-messaging"));

				oneOf(dataStore_mock).getAgentPlatform();
				will(returnValue(agentPlatform_mock));

				oneOf(agentPlatform_mock).fillContent(with(any(ACLMessage.class)), with(event));

				oneOf(subscription_mock).notify(with(new ACLMessageMatcher().expectPerformative(ACLMessage.INFORM)
						.expectLanguage(FIPANames.ContentLanguage.FIPA_SL)
						.expectOntology(EventSubscriptionOntology.ONTOLOGY_NAME)));
			}
		});

		testable.register(subscription_mock);

		testable.notifyAll(event);
	}

	@Test
	public void notifyAll_process_success() throws RefuseException, NotUnderstoodException {
		final Subscription subscription_mock = context.mock(Subscription.class);
		final ACLMessage message_mock = context.mock(ACLMessage.class);
		final AgentPlatform agentPlatform_mock = context.mock(AgentPlatform.class);
		final Event event = new Event();
		event.setId(Event.PROCESS_COMPLETED_SUCCESS);

		context.checking(new Expectations() {
			{
				oneOf(subscription_mock).getMessage();
				will(returnValue(message_mock));

				oneOf(message_mock).getConversationId();
				will(returnValue("process-status"));

				oneOf(dataStore_mock).getAgentPlatform();
				will(returnValue(agentPlatform_mock));

				oneOf(agentPlatform_mock).fillContent(with(any(ACLMessage.class)), with(event));

				oneOf(subscription_mock).notify(with(new ACLMessageMatcher().expectPerformative(ACLMessage.INFORM)
						.expectLanguage(FIPANames.ContentLanguage.FIPA_SL)
						.expectOntology(EventSubscriptionOntology.ONTOLOGY_NAME)));
			}
		});

		testable.register(subscription_mock);

		testable.notifyAll(event);
	}

	@Test
	public void notifyAll_process_failure() throws RefuseException, NotUnderstoodException {
		final Subscription subscription_mock = context.mock(Subscription.class);
		final ACLMessage message_mock = context.mock(ACLMessage.class);
		final AgentPlatform agentPlatform_mock = context.mock(AgentPlatform.class);
		final Event event = new Event();
		event.setId(Event.PROCESS_COMPLETED_FAILURE);

		context.checking(new Expectations() {
			{
				oneOf(subscription_mock).getMessage();
				will(returnValue(message_mock));

				oneOf(message_mock).getConversationId();
				will(returnValue("process-status"));

				oneOf(dataStore_mock).getAgentPlatform();
				will(returnValue(agentPlatform_mock));

				oneOf(agentPlatform_mock).fillContent(with(any(ACLMessage.class)), with(event));

				oneOf(subscription_mock).notify(with(new ACLMessageMatcher().expectPerformative(ACLMessage.INFORM)
						.expectLanguage(FIPANames.ContentLanguage.FIPA_SL)
						.expectOntology(EventSubscriptionOntology.ONTOLOGY_NAME)));
			}
		});

		testable.register(subscription_mock);

		testable.notifyAll(event);
	}

	@Test
	public void notifyAll_event() throws RefuseException, NotUnderstoodException {
		final Subscription subscription_mock = context.mock(Subscription.class);
		final ACLMessage message_mock = context.mock(ACLMessage.class);
		final AgentPlatform agentPlatform_mock = context.mock(AgentPlatform.class);
		final Event event = new Event();
		event.setId("event");

		context.checking(new Expectations() {
			{
				oneOf(subscription_mock).getMessage();
				will(returnValue(message_mock));

				oneOf(message_mock).getConversationId();
				will(returnValue("event"));

				oneOf(dataStore_mock).getAgentPlatform();
				will(returnValue(agentPlatform_mock));

				oneOf(agentPlatform_mock).fillContent(with(any(ACLMessage.class)), with(event));

				oneOf(subscription_mock).notify(with(new ACLMessageMatcher().expectPerformative(ACLMessage.INFORM)
						.expectLanguage(FIPANames.ContentLanguage.FIPA_SL)
						.expectOntology(EventSubscriptionOntology.ONTOLOGY_NAME)));
			}
		});

		testable.register(subscription_mock);

		testable.notifyAll(event);
	}
}
