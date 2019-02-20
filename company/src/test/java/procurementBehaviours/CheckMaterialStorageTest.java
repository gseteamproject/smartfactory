package procurementBehaviours;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.lib.legacy.ClassImposteriser;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import common.AgentDataStore;
import common.AgentPlatform;
import communication.MessageObject;
import interactors.ResponderBehaviour;
import jade.core.Agent;
import jade.lang.acl.ACLMessage;

public class CheckMaterialStorageTest {

	private final Mockery context = new Mockery() {
		{
			this.setImposteriser(ClassImposteriser.INSTANCE);
		}
	};

	CheckMaterialStorage testable;

	ResponderBehaviour responderBehaviour_mock;

	AgentDataStore agentDataStore_mock;

	Agent agent_mock;

	ACLMessage message_mock;

	String content;

	@Before
	public void setUp() {
		responderBehaviour_mock = context.mock(ResponderBehaviour.class);
		agentDataStore_mock = context.mock(AgentDataStore.class);
		agent_mock = context.mock(Agent.class);
		message_mock = context.mock(ACLMessage.class);
		content = "{\"id\":1,\"orderList\":[{\"product\":{\"stone\":{\"size\":10.0,\"price\":0},\"paint\":{\"color\":\"red\",\"price\":0},\"price\":0},\"amount\":1}],\"deadline\":60000,\"price\":100}";

		context.checking(new Expectations() {
			{
				oneOf(responderBehaviour_mock).getAgent();
				will(returnValue(agent_mock));

				oneOf(responderBehaviour_mock).getRequest();
				will(returnValue(message_mock));

				oneOf(message_mock).getContent();
				will(returnValue(content));
			}
		});

		testable = new CheckMaterialStorage(responderBehaviour_mock, agentDataStore_mock);
	}

	@After
	public void tearDown() {
		context.assertIsSatisfied();
	}

	@Test
	public void action_not_in_the_storage() {
		final AgentPlatform agentPlatform_mock = context.mock(AgentPlatform.class);
		final ACLMessage subMessage_mock = context.mock(ACLMessage.class, "subMessage");

		context.checking(new Expectations() {
			{
				oneOf(agentDataStore_mock).setIsInMaterialStorage(true);

				oneOf(agentDataStore_mock).getAgentPlatform();
				will(returnValue(agentPlatform_mock));

				// TODO : add Matcher
				oneOf(agentPlatform_mock).sendMessageToWebClient(with(any(MessageObject.class)));

				oneOf(agentDataStore_mock).setIsInMaterialStorage(false);

				oneOf(agentDataStore_mock).getAgentPlatform();
				will(returnValue(agentPlatform_mock));

				// TODO : add Matcher
				oneOf(agentPlatform_mock).sendMessageToWebClient(with(any(MessageObject.class)));

				oneOf(message_mock).clone();
				will(returnValue(subMessage_mock));

				// TODO : add Matcher
				oneOf(subMessage_mock).setContent(with(any(String.class)));

				oneOf(agentDataStore_mock).setSubMessage(subMessage_mock);

				oneOf(responderBehaviour_mock).getAgent();
				will(returnValue(agent_mock));

				// TODO : add Matcher
				oneOf(agent_mock).addBehaviour(with(any(AskForAuction.class)));
			}
		});

		testable.action();
	}
}
