package sellingBehaviours;

import java.util.Iterator;
import java.util.List;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.lib.legacy.ClassImposteriser;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import basicClasses.CrossAgentData;
import basicClasses.Order;
import basicClasses.OrderPart;
import basicClasses.Product;
import common.AgentDataStore;
import common.AgentPlatform;
import communication.MessageObject;
import interactors.RequestResult;
import interactors.ResponderBehaviour;
import jade.core.Agent;
import jade.lang.acl.ACLMessage;

public class GiveProductToMarketBehaviourTest {

	private final Mockery context = new Mockery() {
		{
			this.setImposteriser(ClassImposteriser.INSTANCE);
		}
	};

	GiveProductToMarketBehaviour testable;

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

		testable = new GiveProductToMarketBehaviour(responderBehaviour_mock, agentDataStore_mock);
	}

	@After
	public void tearDown() {
		context.assertIsSatisfied();
	}

	@SuppressWarnings("unchecked")
	@Test
	public void action() {
		final Order order_mock = context.mock(Order.class);
		final List<OrderPart> list_mock = context.mock(List.class);
		final Iterator<OrderPart> iterator_mock = context.mock(Iterator.class);
		order_mock.orderList = list_mock;
		final OrderPart orderPart_mock = context.mock(OrderPart.class);
		final AgentPlatform agentPlatform_mock = context.mock(AgentPlatform.class);
		final Product product_mock = context.mock(Product.class);
		final RequestResult requestResult_mock = context.mock(RequestResult.class);
		final ACLMessage request_mock = context.mock(ACLMessage.class);

		context.checking(new Expectations() {
			{
				oneOf(agentDataStore_mock).getOrder();
				will(returnValue(order_mock));

				oneOf(agentDataStore_mock).setIsTaken(false);

				oneOf(list_mock).iterator();
				will(returnValue(iterator_mock));

				oneOf(iterator_mock).hasNext();
				will(returnValue(true));

				oneOf(iterator_mock).next();
				will(returnValue(orderPart_mock));

				oneOf(orderPart_mock).getTextOfOrderPart();
				will(returnValue("text"));

				oneOf(agentDataStore_mock).getAgentPlatform();
				will(returnValue(agentPlatform_mock));

				// TODO : add Matcher
				oneOf(agentPlatform_mock).sendMessageToWebClient(with(any(MessageObject.class)));

				oneOf(orderPart_mock).getAmount();
				will(returnValue(1));

				oneOf(orderPart_mock).getProduct();
				will(returnValue(product_mock));

				oneOf(orderPart_mock).getAmount();
				will(returnValue(1));

				oneOf(iterator_mock).hasNext();
				will(returnValue(false));

				oneOf(list_mock).size();
				will(returnValue(1));

				oneOf(agentDataStore_mock).setIsTaken(true);

				oneOf(responderBehaviour_mock).getRequestResult();
				will(returnValue(requestResult_mock));

				oneOf(responderBehaviour_mock).getRequest();
				will(returnValue(request_mock));

				oneOf(requestResult_mock).execute(request_mock);
			}
		});

		CrossAgentData.warehouse.clear();

		testable.action();
	}
}
