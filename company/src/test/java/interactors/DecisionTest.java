package interactors;

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
import jade.core.AID;
import jade.lang.acl.ACLMessage;
import jade.util.leap.Iterator;

public class DecisionTest {

	private final Mockery context = new Mockery() {
		{
			this.setImposteriser(ClassImposteriser.INSTANCE);
		}
	};

	Decision testable;

	ResponderBehaviour responderBehaviour_mock;

	AgentDataStore dataStore_mock;

	@Before
	public void setUp() {
		responderBehaviour_mock = context.mock(ResponderBehaviour.class);
		dataStore_mock = context.mock(AgentDataStore.class);

		testable = new Decision(responderBehaviour_mock, dataStore_mock);
	}

	@After
	public void tearDown() {
		context.assertIsSatisfied();
	}

	@Test
	public void setup() {
		final ACLMessage request_mock = context.mock(ACLMessage.class);
		final Iterator allReceiver_mock = context.mock(Iterator.class);
		final AgentPlatform agentPlatform_mock = context.mock(AgentPlatform.class);
		final DeadlineBehaviour deadlineBehaviour_mock = context.mock(DeadlineBehaviour.class);
		final RequestResult requestResultBehaviour_mock = context.mock(RequestResult.class);
		final AskBehaviour askBehaviour_mock = context.mock(AskBehaviour.class);

		final String agentName = "AgentSelling";
		final long deadline = 120000;
		final String json = "{\"id\":0,\"orderList\":[],\"deadline\":" + (System.currentTimeMillis() + deadline)
				+ ",\"price\":0}";

		context.checking(new Expectations() {
			{
				oneOf(request_mock).getContent();
				will(returnValue(json));

				// TODO : add Order matcher
				oneOf(dataStore_mock).setOrder(with(any(Order.class)));

				oneOf(dataStore_mock).getAgentName();
				will(returnValue(agentName));

				// TODO : add Matcher
				oneOf(request_mock).setContent(with(any(String.class)));

				oneOf(request_mock).getSender();
				will(returnValue(new AID("AgentSalesMarket@testPlatform", AID.ISGUID)));

				oneOf(request_mock).getPerformative();
				will(returnValue(ACLMessage.INFORM));

				oneOf(request_mock).getAllReceiver();
				will(returnValue(allReceiver_mock));

				oneOf(allReceiver_mock).next();
				will(returnValue(new AID("AgentSelling@testPlatform", AID.ISGUID)));

				oneOf(dataStore_mock).getAgentPlatform();
				will(returnValue(agentPlatform_mock));

				// TODO : add Matcher
				oneOf(agentPlatform_mock).sendMessageToWebClient(with(any(MessageObject.class)));

				oneOf(dataStore_mock).setDeadlineResult(false);

				oneOf(responderBehaviour_mock).getDeadlineBehaviour();
				will(returnValue(deadlineBehaviour_mock));

				// TODO : add Matcher
				oneOf(deadlineBehaviour_mock).reset(with(any(long.class)));

				oneOf(responderBehaviour_mock).getRequestResult();
				will(returnValue(requestResultBehaviour_mock));

				oneOf(requestResultBehaviour_mock).reset();

				oneOf(responderBehaviour_mock).getAskBehaviour();
				will(returnValue(askBehaviour_mock));

				oneOf(askBehaviour_mock).setStarted(false);
			}
		});

		testable.setup(request_mock);
	}

	@Test
	public void execute() {
		final ACLMessage request_mock = context.mock(ACLMessage.class);

		Assert.assertNull(testable.execute(request_mock));
	}
}
