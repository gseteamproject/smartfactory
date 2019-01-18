package sellingBehaviours;

import interactors.AchieveREInitiatorInteractorBehaviour;
import interactors.OrderDataStore;
import interactors.ResponderBehaviour;

public class AskToProduceInitiatorBehaviour extends AchieveREInitiatorInteractorBehaviour {

	private static final long serialVersionUID = 9100272572803215372L;

	public AskToProduceInitiatorBehaviour(ResponderBehaviour interactionBehaviour, OrderDataStore dataStore) {
		super(new AskToProduceInitiator(interactionBehaviour, dataStore));
	}
}
