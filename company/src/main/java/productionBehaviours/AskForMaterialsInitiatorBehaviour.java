package productionBehaviours;

import interactors.AchieveREInitiatorInteractorBehaviour;
import interactors.OrderDataStore;

public class AskForMaterialsInitiatorBehaviour extends AchieveREInitiatorInteractorBehaviour {

    /**
     * 
     */
    private static final long serialVersionUID = -1582654090649451918L;

    public AskForMaterialsInitiatorBehaviour(ProductionResponder interactionBehaviour, OrderDataStore dataStore) {
        super(new AskForMaterialsInitiator(interactionBehaviour, dataStore));
    }

}
