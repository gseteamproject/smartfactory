package financesBehaviours;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.lib.legacy.ClassImposteriser;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import basicClasses.Order;
import common.AgentDataStore;
import interactors.RequestResult;
import interactors.ResponderBehaviour;
import jade.lang.acl.ACLMessage;

public class TransferMoneyToBankTest {

	private final Mockery context = new Mockery() {
		{
			this.setImposteriser(ClassImposteriser.INSTANCE);
		}
	};

	TransferMoneyToBank testable;

	ResponderBehaviour responderBehaviour_mock;

	AgentDataStore agentDataStore_mock;

	@Before
	public void setUp() {
		responderBehaviour_mock = context.mock(ResponderBehaviour.class);
		agentDataStore_mock = context.mock(AgentDataStore.class);

		testable = new TransferMoneyToBank(responderBehaviour_mock, agentDataStore_mock);
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
		final RequestResult requestResult_mock = context.mock(RequestResult.class);
		final ACLMessage request_mock = context.mock(ACLMessage.class);

		context.checking(new Expectations() {
			{
				oneOf(agentDataStore_mock).getOrder();
				will(returnValue(order_mock));

				oneOf(order_mock).getTextOfOrder();
				will(returnValue(content));

				oneOf(responderBehaviour_mock).getRequestResult();
				will(returnValue(requestResult_mock));

				oneOf(responderBehaviour_mock).getRequest();
				will(returnValue(request_mock));

				oneOf(requestResult_mock).execute(request_mock);
			}
		});

		testable.action();
	}
}
