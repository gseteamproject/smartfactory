package productionBehaviours;

import interactors.AchieveREInitiatorInteractorBehaviour;
import interactors.OrderDataStore;
import interactors.ResponderBehaviour;

public class TakeFromStorageInitiatorBehaviour extends AchieveREInitiatorInteractorBehaviour {

	private static final long serialVersionUID = 7675608131570849591L;

	public TakeFromStorageInitiatorBehaviour(ResponderBehaviour interactionBehaviour, OrderDataStore dataStore) {
		super(new TakeFromStorageInitiator(interactionBehaviour, dataStore));
	}
}
