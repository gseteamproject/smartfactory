package productionBehaviours;

import interactors.AchieveREInitiatorInteractorBehaviour;
import interactors.OrderDataStore;

public class TakeFromStorageInitiatorBehaviour extends AchieveREInitiatorInteractorBehaviour {

	private static final long serialVersionUID = 7675608131570849591L;

	public TakeFromStorageInitiatorBehaviour(ProductionResponder interactionBehaviour, OrderDataStore dataStore) {
		super(new TakeFromStorageInitiator(interactionBehaviour, dataStore));
	}
}
