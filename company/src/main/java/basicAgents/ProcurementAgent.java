package basicAgents;

import interactors.OrderDataStore;
import jade.core.Agent;
import procurementBehaviours.ProcurementResponder;

public class ProcurementAgent extends Agent {

	private static final long serialVersionUID = 2923962894395399488L;

	protected OrderDataStore dataStore;

	@Override
	protected void setup() {
		dataStore = new OrderDataStore();
		dataStore.setIsInMaterialStorage(false);
		dataStore.setIsGiven(false);

		addBehaviour(new ProcurementResponder(this, dataStore));
	}
}
