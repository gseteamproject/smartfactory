package productionBehaviours;

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
import interactors.ResponderBehaviour;
import jade.core.AID;
import jade.core.Agent;
import jade.lang.acl.ACLMessage;
import jade.util.leap.Iterator;

public class AskForMaterialsInitiatorTest {

	private final Mockery context = new Mockery() {
		{
			this.setImposteriser(ClassImposteriser.INSTANCE);
		}
	};

	AskForMaterialsInitiator testable;

	ResponderBehaviour responderBehaviour_mock;

	AgentDataStore agentDataStore_mock;

	@Before
	public void setUp() {
		responderBehaviour_mock = context.mock(ResponderBehaviour.class);
		agentDataStore_mock = context.mock(AgentDataStore.class);

		testable = new AskForMaterialsInitiator(responderBehaviour_mock, agentDataStore_mock);
	}

	@After
	public void tearDown() {
		context.assertIsSatisfied();
	}

	@Test
	public void handleInform() {
		final ACLMessage message_mock = context.mock(ACLMessage.class, "inform");
		final String content = "{\"id\":0,\"orderList\":[],\"deadline\":" + (System.currentTimeMillis() + 100)
				+ ",\"price\":0}";
		final Iterator iterator = context.mock(Iterator.class);
		final AgentPlatform agentPlatform_mock = context.mock(AgentPlatform.class);
		final Agent agent_mock = context.mock(Agent.class);

		context.checking(new Expectations() {
			{
				oneOf(message_mock).getContent();
				will(returnValue(content));

				oneOf(message_mock).getSender();
				will(returnValue(new AID("AgentSelling@testPlatform", AID.ISGUID)));

				oneOf(message_mock).getPerformative();
				will(returnValue(ACLMessage.INFORM));

				oneOf(message_mock).getAllReceiver();
				will(returnValue(iterator));

				oneOf(iterator).next();
				will(returnValue(new AID("AgentSelling@testPlatform", AID.ISGUID)));

				oneOf(agentDataStore_mock).getAgentPlatform();
				will(returnValue(agentPlatform_mock));

				// TODO : add Matcher
				oneOf(agentPlatform_mock).sendMessageToWebClient(with(any(MessageObject.class)));

				oneOf(responderBehaviour_mock).getAgent();
				will(returnValue(agent_mock));

				oneOf(responderBehaviour_mock).getAgent();
				will(returnValue(agent_mock));

				// TODO : add Matcher
				oneOf(agent_mock).addBehaviour(with(any(TakeFromStorageBehaviour.class)));
			}
		});

		testable.handleInform(message_mock);
	}

	@Test
	public void handleFailure() {
		final ACLMessage message_mock = context.mock(ACLMessage.class, "inform");
		final String content = "{\"id\":0,\"orderList\":[],\"deadline\":" + (System.currentTimeMillis() + 100)
				+ ",\"price\":0}";
		final Iterator iterator = context.mock(Iterator.class);
		final AgentPlatform agentPlatform_mock = context.mock(AgentPlatform.class);

		context.checking(new Expectations() {
			{
				oneOf(message_mock).getContent();
				will(returnValue(content));

				oneOf(message_mock).getSender();
				will(returnValue(new AID("AgentSelling@testPlatform", AID.ISGUID)));

				oneOf(message_mock).getPerformative();
				will(returnValue(ACLMessage.INFORM));

				oneOf(message_mock).getAllReceiver();
				will(returnValue(iterator));

				oneOf(iterator).next();
				will(returnValue(new AID("AgentSelling@testPlatform", AID.ISGUID)));

				oneOf(agentDataStore_mock).getAgentPlatform();
				will(returnValue(agentPlatform_mock));

				// TODO : add Matcher
				oneOf(agentPlatform_mock).sendMessageToWebClient(with(any(MessageObject.class)));
			}
		});

		testable.handleFailure(message_mock);
	}

	@Test
	public void handleAgree() {
		final ACLMessage message_mock = context.mock(ACLMessage.class);

		testable.handleAgree(message_mock);
	}

	@Test
	public void handleRefuse() {
		final ACLMessage message_mock = context.mock(ACLMessage.class);

		testable.handleRefuse(message_mock);
	}

	@Test
	public void next() {
		Assert.assertEquals(0, testable.next());
	}
}
