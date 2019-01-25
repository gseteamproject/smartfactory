package sellingBehaviours;

import common.AgentDataStore;
import interactors.AchieveREInitiatorInteractorBehaviour;
import interactors.ResponderBehaviour;

public class AskToProduceInitiatorBehaviour extends AchieveREInitiatorInteractorBehaviour {

	private static final long serialVersionUID = 9100272572803215372L;

	public AskToProduceInitiatorBehaviour(ResponderBehaviour interactionBehaviour, AgentDataStore dataStore) {
		super(new AskToProduceInitiator(interactionBehaviour, dataStore));
	}
}
