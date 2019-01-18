package salesMarketBehaviours;

import interactors.AchieveREInitiatorInteractorBehaviour;
import interactors.OrderDataStore;
import interactors.ResponderBehaviour;

public class TakeFromWarehouseInitiatorBehaviour extends AchieveREInitiatorInteractorBehaviour {

	private static final long serialVersionUID = 1536353034075733279L;

	public TakeFromWarehouseInitiatorBehaviour(ResponderBehaviour interactionBehaviour, OrderDataStore dataStore) {
		super(new TakeFromWarehouseInitiator(interactionBehaviour, dataStore));
	}
}
