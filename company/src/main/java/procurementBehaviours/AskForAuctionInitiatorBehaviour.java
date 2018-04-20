package procurementBehaviours;

import interactors.AchieveREInitiatorInteractorBehaviour;
import interactors.OrderDataStore;

public class AskForAuctionInitiatorBehaviour extends AchieveREInitiatorInteractorBehaviour {

    /**
     * 
     */
    private static final long serialVersionUID = -362126223538536713L;

    public AskForAuctionInitiatorBehaviour(ProcurementResponder interactionBehaviour, OrderDataStore dataStore) {
        super(new AskForAuctionInitiator(interactionBehaviour, dataStore));
    }
}
