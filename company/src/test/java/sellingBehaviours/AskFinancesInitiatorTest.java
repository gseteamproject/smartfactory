package sellingBehaviours;

import java.util.List;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.lib.legacy.ClassImposteriser;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import basicClasses.Order;
import common.AgentDataStore;
import common.AgentPlatform;
import communication.MessageObject;
import interactors.ResponderBehaviour;
import jade.core.AID;
import jade.core.Agent;
import jade.lang.acl.ACLMessage;
import jade.util.leap.Iterator;

public class AskFinancesInitiatorTest {

	private final Mockery context = new Mockery() {
		{
			this.setImposteriser(ClassImposteriser.INSTANCE);
		}
	};

	AskFinancesInitiator testable;

	ResponderBehaviour responderBehaviour_mock;

	AgentDataStore agentDataStore_mock;

	@Before
	public void setUp() {
		responderBehaviour_mock = context.mock(ResponderBehaviour.class);
		agentDataStore_mock = context.mock(AgentDataStore.class);

		testable = new AskFinancesInitiator(responderBehaviour_mock, agentDataStore_mock);
	}

	@After
	public void tearDown() {
		context.assertIsSatisfied();
	}

	@SuppressWarnings("unchecked")
	@Test
	public void handleInform() {
		final ACLMessage message_mock = context.mock(ACLMessage.class, "inform");
		final String content = "{\"id\":0,\"orderList\":[],\"deadline\":" + (System.currentTimeMillis() + 100)
				+ ",\"price\":0}";
		final Iterator iterator = context.mock(Iterator.class);
		final AgentPlatform agentPlatform_mock = context.mock(AgentPlatform.class);
		final List<Order> productionQueue_mock = context.mock(List.class);
		final ACLMessage subMessage_mock = context.mock(ACLMessage.class, "sub-message");
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

				oneOf(message_mock).getContent();
				will(returnValue(content));

				oneOf(agentDataStore_mock).getAgentPlatform();
				will(returnValue(agentPlatform_mock));

				// TODO : add Matcher
				oneOf(agentPlatform_mock).sendMessageToWebClient(with(any(MessageObject.class)));

				oneOf(message_mock).clone();
				will(returnValue(subMessage_mock));

				oneOf(agentDataStore_mock).setSubMessage(subMessage_mock);

				oneOf(agentDataStore_mock).getProductionQueue();
				will(returnValue(productionQueue_mock));

				// TODO : add Matcher
				oneOf(productionQueue_mock).add(with(any(Order.class)));

				oneOf(responderBehaviour_mock).getAgent();
				will(returnValue(agent_mock));

				oneOf(responderBehaviour_mock).getAgent();
				will(returnValue(agent_mock));

				// TODO : add Matcher
				oneOf(agent_mock).addBehaviour(with(any(AskToProduceBehaviour.class)));
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
				will(returnValue(ACLMessage.FAILURE));

				oneOf(message_mock).getAllReceiver();
				will(returnValue(iterator));

				oneOf(iterator).next();
				will(returnValue(new AID("AgentSelling@testPlatform", AID.ISGUID)));

				oneOf(agentDataStore_mock).getAgentPlatform();
				will(returnValue(agentPlatform_mock));

				// TODO : add Matcher
				oneOf(agentPlatform_mock).sendMessageToWebClient(with(any(MessageObject.class)));

				oneOf(message_mock).getContent();
				will(returnValue(content));

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
				will(returnValue(ACLMessage.AGREE));

				oneOf(message_mock).getAllReceiver();
				will(returnValue(iterator));

				oneOf(iterator).next();
				will(returnValue(new AID("AgentSelling@testPlatform", AID.ISGUID)));

				oneOf(agentDataStore_mock).getAgentPlatform();
				will(returnValue(agentPlatform_mock));

				// TODO : add Matcher
				oneOf(agentPlatform_mock).sendMessageToWebClient(with(any(MessageObject.class)));

				oneOf(message_mock).getContent();
				will(returnValue(content));

				oneOf(agentDataStore_mock).getAgentPlatform();
				will(returnValue(agentPlatform_mock));

				// TODO : add Matcher
				oneOf(agentPlatform_mock).sendMessageToWebClient(with(any(MessageObject.class)));
			}
		});

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
