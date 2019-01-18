package sellingBehaviours;

import interactors.AchieveREInitiatorInteractorBehaviour;
import interactors.OrderDataStore;
import interactors.ResponderBehaviour;

public class AskFinancesInitiatorBehaviour extends AchieveREInitiatorInteractorBehaviour {

	private static final long serialVersionUID = 9100272572803215372L;

	public AskFinancesInitiatorBehaviour(ResponderBehaviour interactionBehaviour, OrderDataStore dataStore) {
		super(new AskFinancesInitiator(interactionBehaviour, dataStore));
	}
}
