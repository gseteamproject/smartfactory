package sellingBehaviours;

import interactors.OrderDataStore;
import jade.core.behaviours.ParallelBehaviour;

public class SellingActivityBehaviour extends ParallelBehaviour {

	private static final long serialVersionUID = 5504974627813962693L;

	// TODO: put this into DataStore

	public SellingActivityBehaviour(SellingResponder interactionBehaviour, OrderDataStore dataStore) {
		super(interactionBehaviour.getAgent(), WHEN_ANY);
		// interactor = new SellingRequestResult(dataStore);

		addSubBehaviour(dataStore.getDeadlineBehaviour());
		addSubBehaviour(dataStore.getAskBehaviour());
	}
}
