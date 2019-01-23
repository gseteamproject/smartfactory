package customerBehaviours;

import java.util.Random;

import basicClasses.CrossAgentData;
import basicClasses.Order;
import basicClasses.Product;
import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.TickerBehaviour;
import jade.domain.FIPANames;
import jade.lang.acl.ACLMessage;

public class GenerateOrdersBehaviour extends TickerBehaviour {

	private static final long serialVersionUID = -7549190406155306008L;

	public GenerateOrdersBehaviour(Agent a, long period) {
		super(a, period);
	}

	@Override
	protected void onTick() {
		ACLMessage orderMsg = new ACLMessage(ACLMessage.REQUEST);
		orderMsg.addReceiver(new AID(("AgentSalesMarket"), AID.ISLOCALNAME));
		orderMsg.setProtocol(FIPANames.InteractionProtocol.FIPA_REQUEST);

		// improvised customer
		// orderMsg.setSender(new AID(("Customer"), AID.ISLOCALNAME));

		Order order = new Order();
		order.id = CrossAgentData.orderQueue.size() + 1;

		Random rand = new Random();
		int randSize;
		int randAmount;
		int randColI;
		String randColS = "";
		for (int i = 0; i < 3; i++) {
			randColI = rand.nextInt(3);
			switch (randColI) {
			case 0:
				randColS = "red";
				break;
			case 1:
				randColS = "blue";
				break;
			case 2:
				randColS = "green";
				break;
			default:
				randColS = "other";
				break;
			}

			randSize = rand.nextInt(10) + 1;
			randAmount = rand.nextInt(100) + 1;

			order.addGood(new Product(randSize, randColS), randAmount);
		}

		String testGson = Order.gson.toJson(order);
		// {"id":1,"orderList":[{"product":{"stone":{"size":10.0,"price":0},"paint":{"color":"blue","price":0},"price":0},"amount":2},{"product":{"stone":{"size":10.0,"price":0},"paint":{"color":"red","price":0},"price":0},"amount":2}]}

		orderMsg.setContent(testGson);
		myAgent.send(orderMsg);
	}
}
