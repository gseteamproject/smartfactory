package interactors;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.lib.legacy.ClassImposteriser;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import common.AgentDataStore;
import common.AgentPlatform;
import communication.MessageObject;
import jade.core.AID;
import jade.domain.FIPANames;
import jade.lang.acl.ACLMessage;
import jade.util.leap.Iterator;
import ontology.CompanyOntology;

public class RequestInteractorTest {

	private final Mockery context = new Mockery() {
		{
			this.setImposteriser(ClassImposteriser.INSTANCE);
		}
	};

	RequestInteractor testable;

	AgentDataStore agentDataStore_mock;

	ResponderBehaviour responderBehaviour_mock;

	@Before
	public void setUp() {
		agentDataStore_mock = context.mock(AgentDataStore.class);
		responderBehaviour_mock = context.mock(ResponderBehaviour.class);

		testable = new RequestInteractor(responderBehaviour_mock, agentDataStore_mock);
	}

	@After
	public void tearDown() {
		context.assertIsSatisfied();
	}

	@Test
	public void setup() {
		final ACLMessage message_mock = context.mock(ACLMessage.class, "message");
		final String action = "action";
		final ACLMessage request_mock = context.mock(ACLMessage.class, "request");
		final String content = "{\"id\":0,\"orderList\":[],\"deadline\":" + (System.currentTimeMillis() + 100)
				+ ",\"price\":0}";

		context.checking(new Expectations() {
			{
				oneOf(message_mock).setConversationId(action);

				oneOf(message_mock).setProtocol(FIPANames.InteractionProtocol.FIPA_REQUEST);

				oneOf(message_mock).setLanguage(FIPANames.ContentLanguage.FIPA_SL);

				oneOf(message_mock).setOntology(CompanyOntology.ONTOLOGY_NAME);

				oneOf(responderBehaviour_mock).getRequest();
				will(returnValue(request_mock));

				oneOf(request_mock).getContent();
				will(returnValue(content));

				oneOf(message_mock).setContent(content);
			}
		});

		testable.setup(message_mock, action, false);

		Assert.assertEquals(1, testable.l.size());
		Assert.assertEquals(message_mock, testable.l.get(0));
	}

	@Test
	public void setup_sub() {
		final ACLMessage message_mock = context.mock(ACLMessage.class, "message");
		final String action = "action";
		final ACLMessage request_mock = context.mock(ACLMessage.class, "request");
		final String content = "{\"id\":0,\"orderList\":[],\"deadline\":" + (System.currentTimeMillis() + 100)
				+ ",\"price\":0}";

		context.checking(new Expectations() {
			{
				oneOf(message_mock).setConversationId(action);

				oneOf(message_mock).setProtocol(FIPANames.InteractionProtocol.FIPA_REQUEST);

				oneOf(message_mock).setLanguage(FIPANames.ContentLanguage.FIPA_SL);

				oneOf(message_mock).setOntology(CompanyOntology.ONTOLOGY_NAME);

				oneOf(agentDataStore_mock).getSubMessage();
				will(returnValue(request_mock));

				oneOf(request_mock).getContent();
				will(returnValue(content));

				oneOf(message_mock).setContent(content);
			}
		});

		testable.setup(message_mock, action, true);

		Assert.assertEquals(1, testable.l.size());
		Assert.assertEquals(message_mock, testable.l.get(0));
	}

	@Test
	public void handleResponse() {
		final ACLMessage response_mock = context.mock(ACLMessage.class, "message");
		final String content = "{\"id\":0,\"orderList\":[],\"deadline\":" + (System.currentTimeMillis() + 100)
				+ ",\"price\":0}";
		final Iterator allReceiver_mock = context.mock(Iterator.class);
		final AgentPlatform agentPlatform_mock = context.mock(AgentPlatform.class);

		context.checking(new Expectations() {
			{
				oneOf(response_mock).getContent();
				will(returnValue(content));

				oneOf(response_mock).getSender();
				will(returnValue(new AID("sender@testPlatform", AID.ISGUID)));

				oneOf(response_mock).getPerformative();
				will(returnValue(ACLMessage.INFORM));

				oneOf(response_mock).getAllReceiver();
				will(returnValue(allReceiver_mock));

				oneOf(allReceiver_mock).next();
				will(returnValue(new AID("receiver@testPlatform", AID.ISGUID)));

				oneOf(agentDataStore_mock).getAgentPlatform();
				will(returnValue(agentPlatform_mock));

				// TODO : add Matcher
				oneOf(agentPlatform_mock).sendMessageToWebClient(with(any(MessageObject.class)));
			}
		});

		testable.handleResponse(response_mock);
	}
}
