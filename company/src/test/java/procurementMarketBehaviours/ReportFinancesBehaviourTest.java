package procurementMarketBehaviours;

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

public class ReportFinancesBehaviourTest {

	private final Mockery context = new Mockery() {
		{
			this.setImposteriser(ClassImposteriser.INSTANCE);
		}
	};

	ReportFinancesBehaviour testable;

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

		testable = new ReportFinancesBehaviour(responderBehaviour_mock, agentDataStore_mock);
	}

	@After
	public void tearDown() {
		context.assertIsSatisfied();
	}

	@Test
	public void action() {
		final Order order_mock = context.mock(Order.class);
		final String content = "{\"id\":1,\"orderList\":[{\"product\":{\"stone\":{\"size\":10.0,\"price\":0},\"paint\":{\"color\":\"red\",\"price\":0},\"price\":0},\"amount\":1},{\"product\":{\"stone\":{\"size\":10.0,\"price\":0},\"paint\":{\"color\":\"blue\",\"price\":0},\"price\":0},\"amount\":2},{\"product\":{\"stone\":{\"size\":10.0,\"price\":0},\"paint\":{\"color\":\"green\",\"price\":0},\"price\":0},\"amount\":3}],\"deadline\":60000,\"price\":100}";
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
				oneOf(agent_mock).addBehaviour(with(any(ReportFinancesInitiatorBehaviour.class)));
			}
		});

		testable.action();
	}
}
