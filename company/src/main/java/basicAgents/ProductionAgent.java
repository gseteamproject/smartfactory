package basicAgents;

import common.AgentDataStore;
import common.AgentPlatform;
import jade.content.lang.sl.SLCodec;
import jade.core.Agent;
import ontology.CompanyOntology;
import productionBehaviours.ProductionResponder;

public class ProductionAgent extends Agent {

	private static final long serialVersionUID = 9064413910591040008L;

	protected AgentDataStore dataStore;

	@Override
	protected void setup() {
		dataStore = new AgentDataStore();
		dataStore.setAgentPlatform(new AgentPlatform(this));
		dataStore.setAgentName(getLocalName());
		dataStore.setIsProduced(false);

		getContentManager().registerLanguage(new SLCodec());
		getContentManager().registerOntology(CompanyOntology.getInstance());

		addBehaviour(new ProductionResponder(this, dataStore));
	}
}
