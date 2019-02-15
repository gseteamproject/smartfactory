package productionBehaviours;

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
import interactors.AskBehaviour;
import interactors.DeadlineBehaviour;
import interactors.RequestResult;
import interactors.ResponderBehaviour;
import jade.core.AID;
import jade.core.Agent;
import jade.lang.acl.ACLMessage;
import jade.util.leap.Iterator;

public class ProductionDecisionTest {

	private final Mockery context = new Mockery() {
		{
			this.setImposteriser(ClassImposteriser.INSTANCE);
		}
	};

	ProductionDecision testable;

	ResponderBehaviour responderBehaviour_mock;

	AgentDataStore agentDataStore_mock;

	@Before
	public void setUp() {
		responderBehaviour_mock = context.mock(ResponderBehaviour.class);
		agentDataStore_mock = context.mock(AgentDataStore.class);

		testable = new ProductionDecision(responderBehaviour_mock, agentDataStore_mock);
	}

	@After
	public void tearDown() {
		context.assertIsSatisfied();
	}

	@Test
	public void execute() {
		final ACLMessage request_mock = context.mock(ACLMessage.class, "request");
		final ACLMessage response_mock = context.mock(ACLMessage.class, "response");
		final String content = "{\"id\":0,\"orderList\":[],\"deadline\":" + (System.currentTimeMillis() + 100)
				+ ",\"price\":0}";
		final Iterator allReceiver_mock = context.mock(Iterator.class);
		final AgentPlatform agentPlatform_mock = context.mock(AgentPlatform.class);
		final DeadlineBehaviour deadlineBehaviour_mock = context.mock(DeadlineBehaviour.class);
		final RequestResult requestResultBehaviour_mock = context.mock(RequestResult.class);
		final AskBehaviour askBehaviour_mock = context.mock(AskBehaviour.class);
		final Agent agent_mock = context.mock(Agent.class);

		context.checking(new Expectations() {
			{
				oneOf(request_mock).getContent();
				will(returnValue(content));

				// TODO : add Order matcher
				oneOf(agentDataStore_mock).setOrder(with(any(Order.class)));

				oneOf(agentDataStore_mock).getAgentName();
				will(returnValue("AgentFinances"));

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

				oneOf(agentDataStore_mock).getAgentPlatform();
				will(returnValue(agentPlatform_mock));

				// TODO : add Matcher
				oneOf(agentPlatform_mock).sendMessageToWebClient(with(any(MessageObject.class)));

				oneOf(agentDataStore_mock).setDeadlineResult(false);

				oneOf(responderBehaviour_mock).getDeadlineBehaviour();
				will(returnValue(deadlineBehaviour_mock));

				// TODO : add Matcher
				oneOf(deadlineBehaviour_mock).reset(with(any(long.class)));

				oneOf(responderBehaviour_mock).getRequestResult();
				will(returnValue(requestResultBehaviour_mock));

				oneOf(requestResultBehaviour_mock).reset();

				oneOf(responderBehaviour_mock).getAskBehaviour();
				will(returnValue(askBehaviour_mock));

				oneOf(askBehaviour_mock).reset();

				//

				oneOf(agentDataStore_mock).getAgentPlatform();
				will(returnValue(agentPlatform_mock));

				// TODO : add Matcher
				oneOf(agentPlatform_mock).sendMessageToWebClient(with(any(MessageObject.class)));

				oneOf(request_mock).createReply();
				will(returnValue(response_mock));

				oneOf(request_mock).getContent();
				will(returnValue(content));

				oneOf(response_mock).setContent(content);

				oneOf(response_mock).setPerformative(ACLMessage.AGREE);

				oneOf(responderBehaviour_mock).getAgent();
				will(returnValue(agent_mock));

				oneOf(response_mock).setSender(null);
			}
		});

		Assert.assertEquals(response_mock, testable.execute(request_mock));
	}
}
