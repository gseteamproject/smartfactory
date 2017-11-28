package smartfactory.behaviours.production;

import jade.core.behaviours.ParallelBehaviour;
import jade.core.behaviours.ThreadedBehaviourFactory;
import smartfactory.dataStores.ProductionDataStore;

public class ActivityBehaviour extends ParallelBehaviour {

	private ThreadedBehaviourFactory tbf = new ThreadedBehaviourFactory();

	public ActivityBehaviour(ProductionProvisioningResponderBehaviour interactionBehaviour,
			ProductionDataStore dataStore) {
		super(interactionBehaviour.getAgent(), WHEN_ANY);
		addSubBehaviour(tbf.wrap(new WorkBehaviour(interactionBehaviour, dataStore)));
		addSubBehaviour(new StatusBehaviour(interactionBehaviour, dataStore));
		addSubBehaviour(new DeadlineBehaviour(interactionBehaviour, dataStore));
	}

	private static final long serialVersionUID = 7273275975062866663L;
}
