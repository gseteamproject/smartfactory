package productionBehaviours;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.lib.legacy.ClassImposteriser;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import basicClasses.Order;
import common.AgentDataStore;
import common.AgentPlatform;
import communication.MessageObject;
import interactors.ResponderBehaviour;
import jade.core.Agent;

public class TakeFromStorageBehaviourTest {

	private final Mockery context = new Mockery() {
		{
			this.setImposteriser(ClassImposteriser.INSTANCE);
		}
	};

	TakeFromStorageBehaviour testable;

	ResponderBehaviour responderBehaviour_mock;

	AgentDataStore agentDataStore_mock;

	Agent agent_mock;

	@Before
	public void setUp() {
		responderBehaviour_mock = context.mock(ResponderBehaviour.class);
		agentDataStore_mock = context.mock(AgentDataStore.class);
		agent_mock = context.mock(Agent.class);

		context.checking(new Expectations() {
			{
				oneOf(responderBehaviour_mock).getAgent();
				will(returnValue(agent_mock));
			}
		});

		testable = new TakeFromStorageBehaviour(responderBehaviour_mock, agentDataStore_mock);
	}

	@After
	public void tearDown() {
		context.assertIsSatisfied();
	}

	@Test
	public void action() {
		final Order order_mock = context.mock(Order.class);
		final String content = "{\"id\":0,\"orderList\":[],\"deadline\":" + (System.currentTimeMillis() + 100)
				+ ",\"price\":0}";
		final AgentPlatform agentPlatform_mock = context.mock(AgentPlatform.class);

		context.checking(new Expectations() {
			{
				oneOf(agentDataStore_mock).getOrder();
				will(returnValue(order_mock));

				oneOf(order_mock).getTextOfOrder();
				will(returnValue(content));

				oneOf(agentDataStore_mock).getAgentPlatform();
				will(returnValue(agentPlatform_mock));

				// TODO : add Matcher
				oneOf(agentPlatform_mock).sendMessageToWebClient(with(any(MessageObject.class)));

				// TODO : add Matcher
				oneOf(agent_mock).addBehaviour(with(any(TakeFromStorageInitiatorBehaviour.class)));
			}
		});

		testable.action();
	}
}
