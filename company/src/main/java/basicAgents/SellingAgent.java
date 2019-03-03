package basicAgents;

import java.util.ArrayList;
import basicClasses.Order;
import common.AgentDataStore;
import common.AgentPlatform;
import jade.content.lang.sl.SLCodec;
import jade.core.Agent;
import ontology.CompanyOntology;
import sellingBehaviours.SellingResponder;

public class SellingAgent extends Agent {

	private static final long serialVersionUID = 7150875080288668056L;

	protected AgentDataStore dataStore;

	@Override
	protected void setup() {
		dataStore = new AgentDataStore();
		dataStore.setAgentPlatform(new AgentPlatform(this));
		dataStore.setAgentName(getLocalName());
		dataStore.setIsTaken(false);
		dataStore.setIsInWarehouse(false);
		dataStore.setProductionQueue(new ArrayList<Order>());

		getContentManager().registerLanguage(new SLCodec());
		getContentManager().registerOntology(CompanyOntology.getInstance());

		addBehaviour(new SellingResponder(this, dataStore));
	}
}
