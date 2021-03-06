package basicAgents;

import common.AgentDataStore;
import common.AgentPlatform;
import jade.content.lang.sl.SLCodec;
import jade.core.Agent;
import ontology.CompanyOntology;
import procurementBehaviours.ProcurementResponder;

public class ProcurementAgent extends Agent {

	private static final long serialVersionUID = 2923962894395399488L;

	protected AgentDataStore dataStore;

	@Override
	protected void setup() {
		dataStore = new AgentDataStore();
		dataStore.setAgentPlatform(new AgentPlatform(this));
		dataStore.setAgentName(getLocalName());
		dataStore.setIsInMaterialStorage(false);
		dataStore.setIsGiven(false);

		getContentManager().registerLanguage(new SLCodec());
		getContentManager().registerOntology(CompanyOntology.getInstance());

		addBehaviour(new ProcurementResponder(this, dataStore));
	}
}
