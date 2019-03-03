package basicAgents;

import common.AgentDataStore;
import common.AgentPlatform;
import financesBehaviours.FinancesResponder;
import jade.content.lang.sl.SLCodec;
import jade.core.Agent;
import ontology.CompanyOntology;

public class FinancesAgent extends Agent {

	private static final long serialVersionUID = 4773016963292343207L;

	protected AgentDataStore dataStore;

	@Override
	protected void setup() {
		dataStore = new AgentDataStore();
		dataStore.setAgentPlatform(new AgentPlatform(this));
		dataStore.setAgentName(getLocalName());

		getContentManager().registerLanguage(new SLCodec());
		getContentManager().registerOntology(CompanyOntology.getInstance());

		addBehaviour(new FinancesResponder(this, dataStore));
	}
}
