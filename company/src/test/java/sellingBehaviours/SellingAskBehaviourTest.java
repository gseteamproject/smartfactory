package sellingBehaviours;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.lib.legacy.ClassImposteriser;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import basicClasses.CrossAgentData;
import basicClasses.Order;
import common.AgentDataStore;
import common.AgentPlatform;
import communication.MessageObject;
import interactors.RequestResult;
import interactors.ResponderBehaviour;
import jade.core.AID;
import jade.core.Agent;
import jade.lang.acl.ACLMessage;
import jade.util.leap.Iterator;

public class SellingAskBehaviourTest {

	private final Mockery context = new Mockery() {
		{
			this.setImposteriser(ClassImposteriser.INSTANCE);
		}
	};

	SellingAskBehaviour testable;

	ResponderBehaviour responderBehaviour_mock;

	AgentDataStore agentDataStore_mock;

	RequestResult requestResult_mock;

	Agent agent_mock;

	@Before
	public void setUp() {
		responderBehaviour_mock = context.mock(ResponderBehaviour.class);
		agentDataStore_mock = context.mock(AgentDataStore.class);
		requestResult_mock = context.mock(RequestResult.class);
		agent_mock = context.mock(Agent.class);

		context.checking(new Expectations() {
			{
				oneOf(responderBehaviour_mock).getAgent();
				will(returnValue(agent_mock));

				oneOf(responderBehaviour_mock).getRequestResult();
				will(returnValue(requestResult_mock));
			}
		});

		testable = new SellingAskBehaviour(responderBehaviour_mock, agentDataStore_mock);
	}

	@After
	public void tearDown() {
		context.assertIsSatisfied();
	}

	@Test
	public void action_Ask() {
		final ACLMessage request_mock = context.mock(ACLMessage.class);
		final String content = "{\"id\":0,\"orderList\":[],\"deadline\":" + (System.currentTimeMillis() + 100)
				+ ",\"price\":0}";
		final Order order = Order.fromJson(content);
		final Iterator allReceiver_mock = context.mock(Iterator.class);
		final AgentPlatform agentPlatform_mock = context.mock(AgentPlatform.class);

		context.checking(new Expectations() {
			{
				oneOf(responderBehaviour_mock).getRequest();
				will(returnValue(request_mock));

				oneOf(request_mock).getContent();
				will(returnValue(content));

				oneOf(request_mock).getConversationId();
				will(returnValue("Ask"));

				oneOf(request_mock).getSender();
				will(returnValue(new AID("AgentSelling@testPlatform", AID.ISGUID)));

				oneOf(request_mock).getPerformative();
				will(returnValue(ACLMessage.INFORM));

				oneOf(request_mock).getAllReceiver();
				will(returnValue(allReceiver_mock));

				oneOf(allReceiver_mock).next();
				will(returnValue(new AID("AgentSelling@testPlatform", AID.ISGUID)));

				oneOf(agentDataStore_mock).getAgentPlatform();
				will(returnValue(agentPlatform_mock));

				// TODO : add Matcher
				oneOf(agentPlatform_mock).sendMessageToWebClient(with(any(MessageObject.class)));

				oneOf(responderBehaviour_mock).getAgent();
				will(returnValue(agent_mock));

				oneOf(responderBehaviour_mock).getAgent();
				will(returnValue(agent_mock));

				oneOf(responderBehaviour_mock).getRequest();
				will(returnValue(request_mock));

				// TODO : add Matcher
				oneOf(agent_mock).addBehaviour(with(any(CheckWarehouseBehaviour.class)));
			}
		});
		CrossAgentData.orderQueue.add(order);

		testable.action();

		Assert.assertEquals(true, testable.isStarted());
	}

	@Test
	public void action_Take() {
		final ACLMessage request_mock = context.mock(ACLMessage.class);
		final String content = "{\"id\":0,\"orderList\":[],\"deadline\":" + (System.currentTimeMillis() + 100)
				+ ",\"price\":0}";
		final Order order = Order.fromJson(content);
		final Iterator allReceiver_mock = context.mock(Iterator.class);
		final AgentPlatform agentPlatform_mock = context.mock(AgentPlatform.class);

		context.checking(new Expectations() {
			{
				oneOf(responderBehaviour_mock).getRequest();
				will(returnValue(request_mock));

				oneOf(request_mock).getContent();
				will(returnValue(content));

				oneOf(request_mock).getConversationId();
				will(returnValue("Take"));

				oneOf(request_mock).getConversationId();
				will(returnValue("Take"));

				oneOf(request_mock).getSender();
				will(returnValue(new AID("AgentSelling@testPlatform", AID.ISGUID)));

				oneOf(request_mock).getPerformative();
				will(returnValue(ACLMessage.INFORM));

				oneOf(request_mock).getAllReceiver();
				will(returnValue(allReceiver_mock));

				oneOf(allReceiver_mock).next();
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
				oneOf(agent_mock).addBehaviour(with(any(GiveProductToMarketBehaviour.class)));
			}
		});

		CrossAgentData.orderQueue.add(order);

		testable.action();

		Assert.assertEquals(true, testable.isStarted());
	}

	@Test
	public void action_Unknown() {
		final ACLMessage request_mock = context.mock(ACLMessage.class);
		final String content = "{\"id\":0,\"orderList\":[],\"deadline\":" + (System.currentTimeMillis() + 100)
				+ ",\"price\":0}";
		final Order order = Order.fromJson(content);

		context.checking(new Expectations() {
			{
				oneOf(responderBehaviour_mock).getRequest();
				will(returnValue(request_mock));

				oneOf(request_mock).getContent();
				will(returnValue(content));

				oneOf(request_mock).getConversationId();
				will(returnValue("UNKNOWN"));

				oneOf(request_mock).getConversationId();
				will(returnValue("UNKNOWN"));
			}
		});

		CrossAgentData.orderQueue.add(order);

		testable.action();

		Assert.assertEquals(true, testable.isStarted());
	}

	@Test
	public void action_not_found() {
		final ACLMessage request_mock = context.mock(ACLMessage.class);
		final String content = "{\"id\":100,\"orderList\":[],\"deadline\":" + (System.currentTimeMillis() + 100)
				+ ",\"price\":0}";

		context.checking(new Expectations() {
			{
				oneOf(responderBehaviour_mock).getRequest();
				will(returnValue(request_mock));

				oneOf(request_mock).getContent();
				will(returnValue(content));
			}
		});

		testable.action();

		Assert.assertEquals(true, testable.isStarted());
	}

	@Test
	public void action_started() {
		testable.setStarted(true);

		testable.action();
	}
}
