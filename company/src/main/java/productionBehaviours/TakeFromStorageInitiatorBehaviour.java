package productionBehaviours;

import common.AgentDataStore;
import interactors.AchieveREInitiatorInteractorBehaviour;
import interactors.ResponderBehaviour;

public class TakeFromStorageInitiatorBehaviour extends AchieveREInitiatorInteractorBehaviour {

	private static final long serialVersionUID = 7675608131570849591L;

	public TakeFromStorageInitiatorBehaviour(ResponderBehaviour interactionBehaviour, AgentDataStore dataStore) {
		super(new TakeFromStorageInitiator(interactionBehaviour, dataStore));
	}
}
