package procurementBehaviours;

import common.AgentDataStore;
import interactors.AchieveREInitiatorInteractorBehaviour;
import interactors.ResponderBehaviour;

public class AskForAuctionInitiatorBehaviour extends AchieveREInitiatorInteractorBehaviour {

	private static final long serialVersionUID = -362126223538536713L;

	public AskForAuctionInitiatorBehaviour(ResponderBehaviour interactionBehaviour, AgentDataStore dataStore) {
		super(new AskForAuctionInitiator(interactionBehaviour, dataStore));
	}
}
