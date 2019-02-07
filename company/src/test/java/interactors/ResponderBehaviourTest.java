package interactors;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.lib.legacy.ClassImposteriser;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import common.AgentDataStore;
import jade.core.Agent;
import jade.core.behaviours.DataStore;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;

public class ResponderBehaviourTest {

	private final Mockery context = new Mockery() {
		{
			this.setImposteriser(ClassImposteriser.INSTANCE);
		}
	};

	ResponderBehaviour testable;

	AgentDataStore agentDataStore_mock;

	Agent agent_mock;

	MessageTemplate messageTemplate;

	@Before
	public void setUp() {
		agentDataStore_mock = context.mock(AgentDataStore.class);
		agent_mock = context.mock(Agent.class);
		messageTemplate = MessageTemplate.MatchAll();

		testable = new ResponderBehaviour(agent_mock, messageTemplate, agentDataStore_mock);
	}

	@After
	public void tearDown() {
		context.assertIsSatisfied();
	}

	@Test
	public void getRequestResult() {
		Assert.assertEquals(null, testable.getRequestResult());
	}

	@Test
	public void getAskBehaviour() {
		Assert.assertEquals(null, testable.getAskBehaviour());
	}

	@Test
	public void getDeadlineBehaviour() {
		Assert.assertNotEquals(null, testable.getDeadlineBehaviour());
	}

	@Test
	public void getRequest() {
		final DataStore dataStore_mock = context.mock(DataStore.class);
		final ACLMessage message_mock = context.mock(ACLMessage.class);

		context.checking(new Expectations() {
			{
				oneOf(dataStore_mock).get(testable.REQUEST_KEY);
				will(returnValue(message_mock));
			}
		});
		testable.setDataStore(dataStore_mock);

		Assert.assertEquals(message_mock, testable.getRequest());
	}

	@Test
	public void setResult() {
		final DataStore dataStore_mock = context.mock(DataStore.class);
		final ACLMessage message_mock = context.mock(ACLMessage.class);

		context.checking(new Expectations() {
			{
				oneOf(dataStore_mock).put(testable.RESULT_NOTIFICATION_KEY, message_mock);
			}
		});
		testable.setDataStore(dataStore_mock);

		testable.setResult(message_mock);
	}

	@Test
	public void setResponse() {
		final DataStore dataStore_mock = context.mock(DataStore.class);
		final ACLMessage message_mock = context.mock(ACLMessage.class);

		context.checking(new Expectations() {
			{
				oneOf(dataStore_mock).put(testable.RESPONSE_KEY, message_mock);
			}
		});
		testable.setDataStore(dataStore_mock);

		testable.setResponse(message_mock);
	}
}
