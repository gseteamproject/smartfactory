package procurementBehaviours;

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
import interactors.RequestResult;
import interactors.ResponderBehaviour;
import jade.core.Agent;
import jade.lang.acl.ACLMessage;

public class ProcurementAskBehaviourTest {

	private final Mockery context = new Mockery() {
		{
			this.setImposteriser(ClassImposteriser.INSTANCE);
		}
	};

	ProcurementAskBehaviour testable;

	ResponderBehaviour responderBehaviour_mock;

	AgentDataStore agentDataStore_mock;

	RequestResult requestResult_mock;

	Agent agent_mock;

	@Before
	public void setUp() {
		responderBehaviour_mock = context.mock(ResponderBehaviour.class);
		agentDataStore_mock = context.mock(AgentDataStore.class);
		agent_mock = context.mock(Agent.class);
		requestResult_mock = context.mock(RequestResult.class);

		context.checking(new Expectations() {
			{
				oneOf(responderBehaviour_mock).getAgent();
				will(returnValue(agent_mock));

				oneOf(responderBehaviour_mock).getRequestResult();
				will(returnValue(requestResult_mock));
			}
		});

		testable = new ProcurementAskBehaviour(responderBehaviour_mock, agentDataStore_mock);
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
	public void action_materials() {
		final ACLMessage request_mock = context.mock(ACLMessage.class);
		final String content = "{\"id\":100,\"orderList\":[],\"deadline\":" + (System.currentTimeMillis() + 100)
				+ ",\"price\":0}";
		final Order order = Order.fromJson(content);

		context.checking(new Expectations() {
			{
				oneOf(request_mock).getConversationId();
				will(returnValue("Materials"));

				oneOf(responderBehaviour_mock).getRequest();
				will(returnValue(request_mock));

				oneOf(request_mock).getContent();
				will(returnValue(content));

				oneOf(responderBehaviour_mock).getAgent();
				will(returnValue(agent_mock));

				oneOf(responderBehaviour_mock).getAgent();
				will(returnValue(agent_mock));

				oneOf(responderBehaviour_mock).getRequest();
				will(returnValue(request_mock));

				oneOf(request_mock).getContent();
				will(returnValue(content));

				// TODO : add Matcher
				oneOf(agent_mock).addBehaviour(with(any(CheckMaterialStorage.class)));
			}
		});
		CrossAgentData.orderQueue.add(order);

		testable.action();
		Assert.assertEquals(true, testable.isStarted());

		CrossAgentData.orderQueue.clear();
	}

	@Test
	public void action_take() {
		final ACLMessage request_mock = context.mock(ACLMessage.class);
		final String content = "{\"id\":100,\"orderList\":[],\"deadline\":" + (System.currentTimeMillis() + 100)
				+ ",\"price\":0}";
		final Order order = Order.fromJson(content);

		context.checking(new Expectations() {
			{
				oneOf(request_mock).getConversationId();
				will(returnValue("Take"));

				oneOf(request_mock).getConversationId();
				will(returnValue("Take"));

				oneOf(responderBehaviour_mock).getRequest();
				will(returnValue(request_mock));

				oneOf(request_mock).getContent();
				will(returnValue(content));

				oneOf(responderBehaviour_mock).getAgent();
				will(returnValue(agent_mock));

				oneOf(responderBehaviour_mock).getAgent();
				will(returnValue(agent_mock));

				// TODO : add Matcher
				oneOf(agent_mock).addBehaviour(with(any(GiveMaterialToProduction.class)));
			}
		});
		CrossAgentData.orderQueue.add(order);

		testable.action();
		Assert.assertEquals(true, testable.isStarted());

		CrossAgentData.orderQueue.clear();
	}

	@Test
	public void action_unknown() {
		final ACLMessage request_mock = context.mock(ACLMessage.class);
		final String content = "{\"id\":100,\"orderList\":[],\"deadline\":" + (System.currentTimeMillis() + 100)
				+ ",\"price\":0}";
		final Order order = Order.fromJson(content);

		context.checking(new Expectations() {
			{
				oneOf(request_mock).getConversationId();
				will(returnValue("UNKNOWN"));

				oneOf(request_mock).getConversationId();
				will(returnValue("UNKNOWN"));

				oneOf(responderBehaviour_mock).getRequest();
				will(returnValue(request_mock));

				oneOf(request_mock).getContent();
				will(returnValue(content));
			}
		});
		CrossAgentData.orderQueue.add(order);

		testable.action();
		Assert.assertEquals(true, testable.isStarted());

		CrossAgentData.orderQueue.clear();
	}
}
