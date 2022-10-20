package communication;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.lib.legacy.ClassImposteriser;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import jade.core.AID;
import jade.lang.acl.ACLMessage;
import jade.util.leap.Iterator;

public class MessageObjectTest {

	private final Mockery context = new Mockery() {
		{
			this.setImposteriser(ClassImposteriser.INSTANCE);
		}
	};

	MessageObject testable;

	ACLMessage message_mock;

	@Before
	public void setUp() {
		message_mock = context.mock(ACLMessage.class);

		Iterator allReceiver_mock = context.mock(Iterator.class);

		context.checking(new Expectations() {
			{
				oneOf(message_mock).getPerformative();
				will(returnValue(ACLMessage.UNKNOWN));

				oneOf(message_mock).getSender();
				will(returnValue(new AID("senderAgent@testPlatform", AID.ISGUID)));

				oneOf(message_mock).getAllReceiver();
				will(returnValue(allReceiver_mock));

				oneOf(allReceiver_mock).next();
				will(returnValue(new AID("receiverAgent@testPlatform", AID.ISGUID)));
			}
		});

		testable = new MessageObject(message_mock, "orderText");
	}

	@After
	public void tearDown() {
		context.assertIsSatisfied();
	}

	@Test
	public void constructor() {
		Assert.assertEquals("NOT-UNDERSTOOD", testable.getPerformative());
		Assert.assertEquals("senderAgent", testable.getSender());
		Assert.assertEquals("receiverAgent", testable.getReceiver());
		Assert.assertEquals("orderText", testable.getOrderText());
	}

	@Test
	public void getColorForAgent_case_01() {
		testable.setReceiver("AgentProcurement");

		Assert.assertEquals("#3CAD00", testable.getColorForAgent());
	}

	@Test
	public void getColorForAgent_case_02() {
		testable.setReceiver("AgentProcurementMarket");

		Assert.assertEquals("#52EA00", testable.getColorForAgent());
	}

	@Test
	public void getColorForAgent_case_03() {
		testable.setReceiver("AgentCapitalMarket");

		Assert.assertEquals("#00A6C4", testable.getColorForAgent());
	}

	@Test
	public void getColorForAgent_case_04() {
		testable.setReceiver("AgentPaintSelling");

		Assert.assertEquals("#C40000", testable.getColorForAgent());
	}

	@Test
	public void getColorForAgent_case_05() {
		testable.setReceiver("AgentSelling");

		Assert.assertEquals("#F2EE00", testable.getColorForAgent());
	}

	@Test
	public void getColorForAgent_case_06() {
		testable.setReceiver("AgentStoneSelling");

		Assert.assertEquals("#8EB19D", testable.getColorForAgent());
	}

	@Test
	public void getColorForAgent_case_07() {
		testable.setReceiver("AgentSalesMarket");

		Assert.assertEquals("#BC00BC", testable.getColorForAgent());
	}

	@Test
	public void getColorForAgent_case_08() {
		testable.setReceiver("AgentProduction");

		Assert.assertEquals("#A0AF79", testable.getColorForAgent());
	}

	@Test
	public void getColorForAgent_case_09() {
		testable.setReceiver("AgentFinances");

		Assert.assertEquals("#006863", testable.getColorForAgent());
	}

	@Test
	public void getColorForAgent_case_10() {
		testable.setReceiver("unknown");

		Assert.assertEquals("#000000", testable.getColorForAgent());
	}

	@Test
	public void getColorForAction_case_01() {
		testable.setSender("AgentProcurement");

		Assert.assertEquals("orange", testable.getColorForAction());
	}

	@Test
	public void getColorForAction_case_02() {
		testable.setSender("AgentProcurementMarket");

		Assert.assertEquals("#0096fa", testable.getColorForAction());
	}

	@Test
	public void getColorForAction_case_03() {
		testable.setSender("AgentCapitalMarket");

		Assert.assertEquals("#111111", testable.getColorForAction());
	}

	@Test
	public void getColorForAction_case_04() {
		testable.setSender("AgentPaintSelling");

		Assert.assertEquals("#0aff96", testable.getColorForAction());
	}

	@Test
	public void getColorForAction_case_05() {
		testable.setSender("AgentSelling");

		Assert.assertEquals("#dc96be", testable.getColorForAction());
	}

	@Test
	public void getColorForAction_case_06() {
		testable.setSender("AgentStoneSelling");

		Assert.assertEquals("#0aff96", testable.getColorForAction());
	}

	@Test
	public void getColorForAction_case_07() {
		testable.setSender("AgentSalesMarket");

		Assert.assertEquals("red", testable.getColorForAction());
	}

	@Test
	public void getColorForAction_case_08() {
		testable.setSender("AgentProduction");

		Assert.assertEquals("#00be00", testable.getColorForAction());
	}

	@Test
	public void getColorForAction_case_09() {
		testable.setSender("AgentFinances");

		Assert.assertEquals("#dcd201", testable.getColorForAction());
	}

	@Test
	public void getColorForAction_case_10() {
		testable.setSender("unknown");

		Assert.assertEquals("#000000", testable.getColorForAction());
	}

	@Test
	public void getColorForPerformative_case_01() {
		testable.setPerformative(ACLMessage.ACCEPT_PROPOSAL);

		Assert.assertEquals("3CAD00", testable.getColorForPerformative());
	}

	@Test
	public void getColorForPerformative_case_02() {
		testable.setPerformative(ACLMessage.AGREE);

		Assert.assertEquals("52EA00", testable.getColorForPerformative());
	}

	@Test
	public void getColorForPerformative_case_03() {
		testable.setPerformative(ACLMessage.CANCEL);

		Assert.assertEquals("00A6C4", testable.getColorForPerformative());
	}

	@Test
	public void getColorForPerformative_case_04() {
		testable.setPerformative(ACLMessage.FAILURE);

		Assert.assertEquals("C40000", testable.getColorForPerformative());
	}

	@Test
	public void getColorForPerformative_case_05() {
		testable.setPerformative(ACLMessage.INFORM);

		Assert.assertEquals("F2EE00", testable.getColorForPerformative());
	}

	@Test
	public void getColorForPerformative_case_06() {
		testable.setPerformative(ACLMessage.REFUSE);

		Assert.assertEquals("8EB19D", testable.getColorForPerformative());
	}

	@Test
	public void getColorForPerformative_case_07() {
		testable.setPerformative(ACLMessage.REQUEST);

		Assert.assertEquals("BC00BC", testable.getColorForPerformative());
	}

	@Test
	public void getColorForPerformative_case_08() {
		testable.setPerformative(ACLMessage.NOT_UNDERSTOOD);

		Assert.assertEquals("FFFFFF", testable.getColorForPerformative());
	}

	@Test
	public void setOrderText() {
		final String orderText = "abc";

		testable.setOrderText(orderText);

		Assert.assertEquals(orderText, testable.getOrderText());
	}

	@Test
	public void getOrderText() {
		Assert.assertEquals("orderText", testable.getOrderText());
	}

	@Test
	public void setMessage() {
		final String message = "message";

		testable.setMessage(message);

		Assert.assertEquals(message, testable.getMessage());
	}

	@Test
	public void getMessage() {
		Assert.assertEquals(null, testable.getMessage());
	}

	@Test
	public void getActionMessage() {
		Assert.assertEquals("senderAgent: orderText", testable.getActionMessage());
	}
}
