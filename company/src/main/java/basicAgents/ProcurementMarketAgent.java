package basicAgents;

import common.AgentDataStore;
import common.AgentPlatform;
import jade.content.lang.sl.SLCodec;
import jade.core.Agent;
import ontology.CompanyOntology;
import procurementMarketBehaviours.ProcurementMarketResponder;

public class ProcurementMarketAgent extends Agent {

	private static final long serialVersionUID = -7418692714860762106L;

	protected AgentDataStore dataStore;

	@Override
	protected void setup() {
		dataStore = new AgentDataStore();
		dataStore.setAgentPlatform(new AgentPlatform(this));
		dataStore.setAgentName(getLocalName());

		getContentManager().registerLanguage(new SLCodec());
		getContentManager().registerOntology(CompanyOntology.getInstance());

		addBehaviour(new ProcurementMarketResponder(this, dataStore));
	}
}
