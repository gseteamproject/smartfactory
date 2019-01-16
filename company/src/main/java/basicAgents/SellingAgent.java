package basicAgents;

import java.util.ArrayList;
import java.util.List;

import basicClasses.Order;
import basicClasses.ProductStorage;
import interactors.OrderDataStore;
import jade.core.Agent;
import jade.domain.FIPANames;
import jade.lang.acl.MessageTemplate;
import jade.proto.AchieveREResponder;
import sellingBehaviours.SellingResponder;

public class SellingAgent extends Agent {

	private static final long serialVersionUID = 7150875080288668056L;

	public boolean isInWarehouse;

	public boolean isTaken;

	public long currentDeadline;

	protected OrderDataStore dataStore;

	// queue for orders that in production
	public List<Order> productionQueue = new ArrayList<Order>();

	/*
	 * TODO: remove static modifier from variable
	 *
	 * this variable is used by two different agents and has strong coupling
	 */
	// creating storage for products
	public static ProductStorage warehouse = new ProductStorage();

	@Override
	protected void setup() {
		MessageTemplate reqTemp = AchieveREResponder.createMessageTemplate(FIPANames.InteractionProtocol.FIPA_REQUEST);

		dataStore = new OrderDataStore();
		dataStore.setThisAgent(this);

		addBehaviour(new SellingResponder(this, reqTemp, dataStore));
	}
}
