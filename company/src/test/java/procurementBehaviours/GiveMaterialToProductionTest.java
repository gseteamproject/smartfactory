package procurementBehaviours;

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
import basicClasses.Paint;
import basicClasses.Product;
import basicClasses.Stone;
import common.AgentDataStore;
import common.AgentPlatform;
import communication.MessageObject;
import interactors.RequestResult;
import interactors.ResponderBehaviour;
import jade.core.Agent;
import jade.lang.acl.ACLMessage;

public class GiveMaterialToProductionTest {

	private final Mockery context = new Mockery() {
		{
			this.setImposteriser(ClassImposteriser.INSTANCE);
		}
	};

	GiveMaterialToProduction testable;

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

		testable = new GiveMaterialToProduction(responderBehaviour_mock, agentDataStore_mock);
	}

	@After
	public void tearDown() {
		context.assertIsSatisfied();
	}

	@SuppressWarnings("unchecked")
	@Test
	public void action() {
		final Order order_mock = context.mock(Order.class);
		final List<OrderPart> list_mock = context.mock(List.class, "orderList");
		order_mock.orderList = list_mock;
		final Iterator<OrderPart> iterator_mock = context.mock(Iterator.class);
		final String content = "{\"id\":0,\"orderList\":[],\"deadline\":" + (System.currentTimeMillis() + 100)
				+ ",\"price\":0}";
		final AgentPlatform agentPlatform_mock = context.mock(AgentPlatform.class);
		final OrderPart orderPart_mock = context.mock(OrderPart.class);
		final Product product_mock = context.mock(Product.class);
		final Paint paint_mock = context.mock(Paint.class);
		final Stone stone_mock = context.mock(Stone.class);
		final RequestResult requestResult_mock = context.mock(RequestResult.class);
		final ACLMessage request_mock = context.mock(ACLMessage.class, "request");

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

				oneOf(agentDataStore_mock).setIsGiven(false);

				oneOf(list_mock).iterator();
				will(returnValue(iterator_mock));

				oneOf(iterator_mock).hasNext();
				will(returnValue(true));

				oneOf(iterator_mock).next();
				will(returnValue(orderPart_mock));

				oneOf(orderPart_mock).getAmount();
				will(returnValue(1));

				oneOf(orderPart_mock).getGood();
				will(returnValue(product_mock));

				oneOf(product_mock).getPaint();
				will(returnValue(paint_mock));

				oneOf(product_mock).getStone();
				will(returnValue(stone_mock));

				oneOf(paint_mock).equals(paint_mock);
				will(returnValue(true));

				oneOf(stone_mock).equals(stone_mock);
				will(returnValue(true));

				oneOf(orderPart_mock).getAmount();
				will(returnValue(1));

				oneOf(iterator_mock).hasNext();
				will(returnValue(false));

				oneOf(agentDataStore_mock).setIsGiven(true);

				oneOf(responderBehaviour_mock).getRequestResult();
				will(returnValue(requestResult_mock));

				oneOf(responderBehaviour_mock).getRequest();
				will(returnValue(request_mock));

				oneOf(requestResult_mock).execute(request_mock);

				oneOf(responderBehaviour_mock).getAgent();
				will(returnValue(agent_mock));

				oneOf(order_mock).getTextOfOrder();
				will(returnValue(content));

				oneOf(agentDataStore_mock).getAgentPlatform();
				will(returnValue(agentPlatform_mock));

				// TODO : add Matcher
				oneOf(agentPlatform_mock).sendMessageToWebClient(with(any(MessageObject.class)));
			}
		});
		CrossAgentData.materialStorage.add(paint_mock);
		CrossAgentData.materialStorage.add(stone_mock);
		CrossAgentData.procurementQueue.add(order_mock);

		testable.action();
	}
}
