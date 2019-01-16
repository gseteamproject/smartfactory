package salesMarketBehaviours;

import interactors.OrderDataStore;
import jade.core.behaviours.ParallelBehaviour;

public class SalesMarketActivityBehaviour extends ParallelBehaviour {

	private static final long serialVersionUID = -3030187281731033803L;

	public SalesMarketActivityBehaviour(SalesMarketResponder interactionBehaviour, OrderDataStore dataStore) {
		super(interactionBehaviour.getAgent(), WHEN_ANY);

		// addSubBehaviour(new DeadlineBehaviour(interactionBehaviour, interactor,
		// dataStore));
		addSubBehaviour(dataStore.getDeadlineBehaviour());
		// addSubBehaviour(new SalesMarketDeadlineBehaviour(interactionBehaviour,
		// interactor, dataStore));
		// addSubBehaviour(new AskForOrderBehaviour((SalesMarketResponder)
		// interactionBehaviour, dataStore));
		addSubBehaviour(dataStore.getAskBehaviour());
	}
}
