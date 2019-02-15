package productionBehaviours;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.lib.legacy.ClassImposteriser;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import basicClasses.CrossAgentData;
import common.AgentDataStore;
import common.AgentPlatform;
import communication.MessageObject;
import interactors.RequestResult;
import interactors.ResponderBehaviour;
import jade.core.Agent;
import jade.lang.acl.ACLMessage;

public class DeliverToSellingBehaviourTest {

	private final Mockery context = new Mockery() {
		{
			this.setImposteriser(ClassImposteriser.INSTANCE);
		}
	};

	DeliverToSellingBehaviour testable;

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

		testable = new DeliverToSellingBehaviour(responderBehaviour_mock, agentDataStore_mock);
	}

	@After
	public void tearDown() {
		context.assertIsSatisfied();
	}

	@Test
	public void action() {
		final ACLMessage request_mock = context.mock(ACLMessage.class, "request");
		final String content = "{\"id\":1,\"orderList\":[{\"product\":{\"stone\":{\"size\":10.0,\"price\":0},\"paint\":{\"color\":\"red\",\"price\":0},\"price\":0},\"amount\":1},{\"product\":{\"stone\":{\"size\":10.0,\"price\":0},\"paint\":{\"color\":\"blue\",\"price\":0},\"price\":0},\"amount\":2},{\"product\":{\"stone\":{\"size\":10.0,\"price\":0},\"paint\":{\"color\":\"green\",\"price\":0},\"price\":0},\"amount\":3}],\"deadline\":60000,\"price\":100}";
		final AgentPlatform agentPlatform_mock = context.mock(AgentPlatform.class);
		final RequestResult requestResult_mock = context.mock(RequestResult.class);

		context.checking(new Expectations() {
			{
				oneOf(responderBehaviour_mock).getRequest();
				will(returnValue(request_mock));

				oneOf(request_mock).getContent();
				will(returnValue(content));

				oneOf(agentDataStore_mock).getAgentPlatform();
				will(returnValue(agentPlatform_mock));

				// TODO : add Matcher
				oneOf(agentPlatform_mock).sendMessageToWebClient(with(any(MessageObject.class)));

				oneOf(agentDataStore_mock).setIsProduced(true);

				oneOf(responderBehaviour_mock).getRequestResult();
				will(returnValue(requestResult_mock));

				oneOf(responderBehaviour_mock).getRequest();
				will(returnValue(request_mock));

				oneOf(requestResult_mock).execute(request_mock);
			}
		});
		CrossAgentData.warehouse.clear();

		testable.action();
		Assert.assertEquals(6, CrossAgentData.warehouse.size());

		CrossAgentData.warehouse.clear();
	}
}
