package basicAgents;

import java.util.ArrayList;
import java.util.List;

import basicClasses.Order;
import interactors.OrderDataStore;
import jade.core.Agent;
import jade.domain.FIPANames;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import salesMarketBehaviours.SalesMarketResponder;

public class SalesMarketAgent extends Agent {

	private static final long serialVersionUID = 2003110338808844985L;

	// TODO : remove static modifier from members

	// creating list of orders
	public static List<Order> orderQueue = new ArrayList<Order>();
	public static long currentDeadline;
	protected OrderDataStore dataStore;

	@Override
	protected void setup() {
		MessageTemplate temp = MessageTemplate.MatchProtocol(FIPANames.InteractionProtocol.FIPA_REQUEST);
		MessageTemplate reqTemp = MessageTemplate.and(temp, MessageTemplate.MatchPerformative(ACLMessage.REQUEST));

		dataStore = new OrderDataStore();

		// adding behaviours
		addBehaviour(new SalesMarketResponder(this, reqTemp, dataStore));
	}
}
