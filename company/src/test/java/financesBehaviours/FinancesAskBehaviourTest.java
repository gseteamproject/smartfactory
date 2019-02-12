package financesBehaviours;

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

public class FinancesAskBehaviourTest {

	private final Mockery context = new Mockery() {
		{
			this.setImposteriser(ClassImposteriser.INSTANCE);
		}
	};

	FinancesAskBehaviour testable;

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

		testable = new FinancesAskBehaviour(responderBehaviour_mock, agentDataStore_mock);
	}

	@After
	public void tearDown() {
		context.assertIsSatisfied();
	}

	@Test
	public void action_started() {
		testable.setStarted(true);

		testable.action();
	}

	@Test
	public void action_Order() {
		final Order order_mock = context.mock(Order.class);
		final ACLMessage request_mock = context.mock(ACLMessage.class);
		final String content = "{\"id\":0,\"orderList\":[],\"deadline\":" + (System.currentTimeMillis() + 100)
				+ ",\"price\":0}";
		final Iterator allReceiver_mock = context.mock(Iterator.class);
		final AgentPlatform agentPlatform_mock = context.mock(AgentPlatform.class);

		context.checking(new Expectations() {
			{
				oneOf(agentDataStore_mock).getOrder();
				will(returnValue(order_mock));

				oneOf(responderBehaviour_mock).getRequest();
				will(returnValue(request_mock));

				oneOf(order_mock).searchInList(CrossAgentData.orderQueue);
				will(returnValue(0));

				oneOf(request_mock).getConversationId();
				will(returnValue("Order"));

				oneOf(order_mock).getTextOfOrder();
				will(returnValue(content));

				oneOf(request_mock).getSender();
				will(returnValue(new AID("AgentFinances@testPlatform", AID.ISGUID)));

				oneOf(request_mock).getPerformative();
				will(returnValue(ACLMessage.INFORM));

				oneOf(request_mock).getAllReceiver();
				will(returnValue(allReceiver_mock));

				oneOf(allReceiver_mock).next();
				will(returnValue(new AID("AgentFinances@testPlatform", AID.ISGUID)));

				oneOf(agentDataStore_mock).getAgentPlatform();
				will(returnValue(agentPlatform_mock));

				// TODO : add Matcher
				oneOf(agentPlatform_mock).sendMessageToWebClient(with(any(MessageObject.class)));

				oneOf(order_mock).searchInList(CrossAgentData.orderQueue);
				will(returnValue(0));

				// TODO : add Matcher
				oneOf(agent_mock).addBehaviour(with(any(TransferMoneyToBank.class)));
			}
		});

		CrossAgentData.orderQueue.add(order_mock);

		testable.action();

		Assert.assertEquals(true, testable.isStarted());
	}

	@Test
	public void action_Materials() {
		final Order order_mock = context.mock(Order.class);
		final ACLMessage request_mock = context.mock(ACLMessage.class);
		final String content = "{\"id\":0,\"orderList\":[],\"deadline\":" + (System.currentTimeMillis() + 100)
				+ ",\"price\":0}";
		final Iterator allReceiver_mock = context.mock(Iterator.class);
		final AgentPlatform agentPlatform_mock = context.mock(AgentPlatform.class);

		context.checking(new Expectations() {
			{
				oneOf(agentDataStore_mock).getOrder();
				will(returnValue(order_mock));

				oneOf(responderBehaviour_mock).getRequest();
				will(returnValue(request_mock));

				oneOf(order_mock).searchInList(CrossAgentData.orderQueue);
				will(returnValue(0));

				exactly(2).of(request_mock).getConversationId();
				will(returnValue("Materials"));

				oneOf(order_mock).getTextOfOrder();
				will(returnValue(content));

				oneOf(request_mock).getSender();
				will(returnValue(new AID("AgentFinances@testPlatform", AID.ISGUID)));

				oneOf(request_mock).getPerformative();
				will(returnValue(ACLMessage.INFORM));

				oneOf(request_mock).getAllReceiver();
				will(returnValue(allReceiver_mock));

				oneOf(allReceiver_mock).next();
				will(returnValue(new AID("AgentFinances@testPlatform", AID.ISGUID)));

				oneOf(agentDataStore_mock).getAgentPlatform();
				will(returnValue(agentPlatform_mock));

				// TODO : add Matcher
				oneOf(agentPlatform_mock).sendMessageToWebClient(with(any(MessageObject.class)));

				oneOf(order_mock).searchInList(CrossAgentData.orderQueue);
				will(returnValue(0));

				// TODO : add Matcher
				oneOf(agent_mock).addBehaviour(with(any(TransferMoneyFromBank.class)));
			}
		});

		CrossAgentData.orderQueue.add(order_mock);

		testable.action();

		Assert.assertEquals(true, testable.isStarted());
	}

	@Test
	public void action_Unknown() {
		final Order order_mock = context.mock(Order.class);
		final ACLMessage request_mock = context.mock(ACLMessage.class);

		context.checking(new Expectations() {
			{
				oneOf(agentDataStore_mock).getOrder();
				will(returnValue(order_mock));

				oneOf(responderBehaviour_mock).getRequest();
				will(returnValue(request_mock));

				oneOf(order_mock).searchInList(CrossAgentData.orderQueue);
				will(returnValue(0));

				exactly(2).of(request_mock).getConversationId();
				will(returnValue("Unknown"));
			}
		});

		CrossAgentData.orderQueue.add(order_mock);

		testable.action();

		Assert.assertEquals(true, testable.isStarted());
	}

	@Test
	public void action_not_found() {
		final Order order_mock = context.mock(Order.class);
		final ACLMessage request_mock = context.mock(ACLMessage.class);

		context.checking(new Expectations() {
			{
				oneOf(agentDataStore_mock).getOrder();
				will(returnValue(order_mock));

				oneOf(responderBehaviour_mock).getRequest();
				will(returnValue(request_mock));

				oneOf(order_mock).searchInList(CrossAgentData.orderQueue);
				will(returnValue(-1));
			}
		});

		testable.action();

		Assert.assertEquals(true, testable.isStarted());
	}
}
