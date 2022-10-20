package smartfactory.interactors;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.lib.legacy.ClassImposteriser;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import jade.content.ContentElement;
import jade.lang.acl.ACLMessage;
import smartfactory.platform.AgentPlatform;
import smartfactory.utility.AgentDataStore;

public class InteractorTest {

	private final Mockery context = new Mockery() {
		{
			this.setImposteriser(ClassImposteriser.INSTANCE);
		}
	};

	Interactor testable;

	AgentDataStore dataStore_mock;

	@Before
	public void setUp() {
		dataStore_mock = context.mock(AgentDataStore.class);

		testable = new Interactor(dataStore_mock);
	}

	@After
	public void tearDown() {
		context.assertIsSatisfied();
	}

	@Test
	public void extractContent() {
		final ACLMessage message_mock = context.mock(ACLMessage.class);
		final ContentElement contentElement_mock = context.mock(ContentElement.class);
		final AgentPlatform agentPlatform_mock = context.mock(AgentPlatform.class);

		context.checking(new Expectations() {
			{
				oneOf(dataStore_mock).getAgentPlatform();
				will(returnValue(agentPlatform_mock));

				oneOf(agentPlatform_mock).extractContent(message_mock);
				will(returnValue(contentElement_mock));
			}
		});

		Assert.assertEquals(contentElement_mock, testable.extractContent(message_mock));
	}

	@Test
	public void fillContent() {
		final ACLMessage message_mock = context.mock(ACLMessage.class);
		final ContentElement contentElement_mock = context.mock(ContentElement.class);
		final AgentPlatform agentPlatform_mock = context.mock(AgentPlatform.class);

		context.checking(new Expectations() {
			{
				oneOf(dataStore_mock).getAgentPlatform();
				will(returnValue(agentPlatform_mock));

				oneOf(agentPlatform_mock).fillContent(message_mock, contentElement_mock);
			}
		});

		testable.fillContent(message_mock, contentElement_mock);
	}
}
