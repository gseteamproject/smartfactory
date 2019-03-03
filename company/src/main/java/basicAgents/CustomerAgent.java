package basicAgents;

import common.AgentDataStore;
import common.AgentPlatform;
import customerBehaviours.OneOrderBehaviour;
import jade.content.lang.sl.SLCodec;
import jade.core.Agent;
import ontology.CompanyOntology;

public class CustomerAgent extends Agent {

	private static final long serialVersionUID = -3320954039785467867L;

	protected AgentDataStore dataStore;

	@Override
	protected void setup() {
		dataStore = new AgentDataStore();
		dataStore.setAgentPlatform(new AgentPlatform(this));
		dataStore.setAgentName(getLocalName());

		getContentManager().registerLanguage(new SLCodec());
		getContentManager().registerOntology(CompanyOntology.getInstance());

		// addBehaviour(new GenerateOrdersBehaviour(this, 15000));
		addBehaviour(new OneOrderBehaviour(this, dataStore));
	}
}
