package productionBehaviours;

import interactors.DecisionBehaviour;
import interactors.OrderDataStore;
import interactors.ResponderBehaviour;

public class ProductionDecisionBehaviour extends DecisionBehaviour {

	private static final long serialVersionUID = -5138036682748995317L;

	public ProductionDecisionBehaviour(ResponderBehaviour interactionBehaviour, OrderDataStore dataStore) {
		super(interactionBehaviour);
		this.interactor = new ProductionDecision(interactionBehaviour, dataStore);
	}
}
