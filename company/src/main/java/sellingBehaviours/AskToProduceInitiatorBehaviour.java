package sellingBehaviours;

import interactors.AchieveREInitiatorInteractorBehaviour;
import interactors.OrderDataStore;

public class AskToProduceInitiatorBehaviour extends AchieveREInitiatorInteractorBehaviour {

	private static final long serialVersionUID = 9100272572803215372L;

	public AskToProduceInitiatorBehaviour(OrderDataStore dataStore) {
		super(new AskToProduceInitiator(dataStore));
	}
}
