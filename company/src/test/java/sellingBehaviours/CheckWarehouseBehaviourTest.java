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
import interactors.ResponderBehaviour;
import jade.core.Agent;
import jade.lang.acl.ACLMessage;

public class CheckWarehouseBehaviourTest {

	private final Mockery context = new Mockery() {
		{
			this.setImposteriser(ClassImposteriser.INSTANCE);
		}
	};

	CheckWarehouseBehaviour testable;

	ResponderBehaviour responderBehaviour_mock;

	AgentDataStore agentDataStore_mock;

	Agent agent_mock;

	ACLMessage message_mock;

	@Before
	public void setUp() {
		responderBehaviour_mock = context.mock(ResponderBehaviour.class);
		agentDataStore_mock = context.mock(AgentDataStore.class);
		agent_mock = context.mock(Agent.class);
		message_mock = context.mock(ACLMessage.class, "request");

		context.checking(new Expectations() {
			{
				oneOf(responderBehaviour_mock).getAgent();
				will(returnValue(agent_mock));

				oneOf(responderBehaviour_mock).getRequest();
				will(returnValue(message_mock));
			}
		});

		testable = new CheckWarehouseBehaviour(responderBehaviour_mock, agentDataStore_mock);
	}

	@After
	public void tearDown() {
		context.assertIsSatisfied();
	}

	@SuppressWarnings("unchecked")
	@Test
	public void action_order_is_queue() {
		final Order order_mock = context.mock(Order.class);
		final List<OrderPart> list_mock = context.mock(List.class, "orderList");
		final Iterator<OrderPart> iterator_mock = context.mock(Iterator.class);
		order_mock.orderList = list_mock;
		final List<Order> productionQueue_mock = context.mock(List.class);
		final OrderPart orderPart_mock = context.mock(OrderPart.class);
		final Product product_mock = context.mock(Product.class);

		context.checking(new Expectations() {
			{
				oneOf(agentDataStore_mock).getOrder();
				will(returnValue(order_mock));

				oneOf(agentDataStore_mock).setIsInWarehouse(true);

				oneOf(agentDataStore_mock).getProductionQueue();
				will(returnValue(productionQueue_mock));

				oneOf(productionQueue_mock).contains(order_mock);
				will(returnValue(true));

				oneOf(list_mock).iterator();
				will(returnValue(iterator_mock));

				oneOf(iterator_mock).hasNext();
				will(returnValue(true));

				oneOf(iterator_mock).next();
				will(returnValue(orderPart_mock));

				oneOf(orderPart_mock).getProduct();
				will(returnValue(product_mock));

				oneOf(orderPart_mock).getAmount();
				will(returnValue(1));

				oneOf(product_mock).equals(product_mock);
				will(returnValue(true));

				oneOf(agentDataStore_mock).getIsInWarehouse();
				will(returnValue(true));

				oneOf(agentDataStore_mock).setIsInWarehouse(true);

				oneOf(iterator_mock).hasNext();
				will(returnValue(false));
			}
		});

		CrossAgentData.warehouse.add(product_mock);

		testable.action();
	}

	@SuppressWarnings("unchecked")
	@Test
	public void action_order_is_not_in_queue() {
		final Order order_mock = context.mock(Order.class);
		final List<OrderPart> list_mock = context.mock(List.class, "orderList");
		final Iterator<OrderPart> iterator_mock = context.mock(Iterator.class, "productionQueue");
		order_mock.orderList = list_mock;
		final List<Order> productionQueue_mock = context.mock(List.class);
		final OrderPart orderPart_mock = context.mock(OrderPart.class);
		final Product product_mock = context.mock(Product.class);
		final ACLMessage subMessage_mock = context.mock(ACLMessage.class, "subMessage");
		final AgentPlatform agentPlatform_mock = context.mock(AgentPlatform.class);

		context.checking(new Expectations() {
			{
				oneOf(agentDataStore_mock).getOrder();
				will(returnValue(order_mock));

				oneOf(agentDataStore_mock).setIsInWarehouse(true);

				oneOf(agentDataStore_mock).getProductionQueue();
				will(returnValue(productionQueue_mock));

				oneOf(productionQueue_mock).contains(order_mock);
				will(returnValue(false));

				oneOf(list_mock).iterator();
				will(returnValue(iterator_mock));

				oneOf(iterator_mock).hasNext();
				will(returnValue(true));

				oneOf(iterator_mock).next();
				will(returnValue(orderPart_mock));

				oneOf(orderPart_mock).getProduct();
				will(returnValue(product_mock));

				oneOf(orderPart_mock).getAmount();
				will(returnValue(1));

				oneOf(agentDataStore_mock).setIsInWarehouse(false);

				oneOf(orderPart_mock).getProduct();
				will(returnValue(product_mock));

				oneOf(orderPart_mock).getAmount();
				will(returnValue(1));

				oneOf(iterator_mock).hasNext();
				will(returnValue(false));

				oneOf(message_mock).clone();
				will(returnValue(subMessage_mock));

				// TODO : add Matcher
				oneOf(subMessage_mock).setContent(with(any(String.class)));

				oneOf(agentDataStore_mock).setSubMessage(subMessage_mock);

				oneOf(product_mock).getColor();
				will(returnValue("color"));

				oneOf(product_mock).getSize();
				will(returnValue(1.0));

				oneOf(agentDataStore_mock).getAgentPlatform();
				will(returnValue(agentPlatform_mock));

				// TODO : add Matcher
				oneOf(agentPlatform_mock).sendMessageToWebClient(with(any(MessageObject.class)));

				oneOf(responderBehaviour_mock).getAgent();
				will(returnValue(agent_mock));

				// TODO : add Matcher
				oneOf(agent_mock).addBehaviour(with(any(AskFinancesBehaviour.class)));
			}
		});

		testable.action();
	}
}
