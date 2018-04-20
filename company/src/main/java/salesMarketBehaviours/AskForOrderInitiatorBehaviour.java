package salesMarketBehaviours;

import interactors.AchieveREInitiatorInteractorBehaviour;
import interactors.OrderDataStore;

public class AskForOrderInitiatorBehaviour extends AchieveREInitiatorInteractorBehaviour {

    /**
     * 
     */
    private static final long serialVersionUID = -6112250909502569706L;

    public AskForOrderInitiatorBehaviour(SalesMarketResponder interactionBehaviour, OrderDataStore dataStore) {
        super(new AskForOrderInitiator(interactionBehaviour, dataStore));
    }

}
