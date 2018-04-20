package procurementBehaviours;

import interactors.DeadlineBehaviour;
import interactors.OrderDataStore;
import jade.core.behaviours.ParallelBehaviour;

public class ProcurementActivityBehaviour extends ParallelBehaviour {

    /**
     * 
     */
    private static final long serialVersionUID = -6703040253614653144L;

    public ProcurementActivityBehaviour(ProcurementResponder interactionBehaviour, ProcurementRequestResult interactor,
            OrderDataStore dataStore) {
        super(interactionBehaviour.getAgent(), WHEN_ANY);

        // addSubBehaviour(new ProcurementAskBehaviour(interactionBehaviour, interactor, dataStore));
        addSubBehaviour(new DeadlineBehaviour((ProcurementResponder) interactionBehaviour, (ProcurementRequestResult) interactor, dataStore));
//        addSubBehaviour(new ProcurementDeadlineBehaviour(interactionBehaviour, interactor, dataStore));
        if (dataStore.getRequestMessage().getConversationId() == "Materials") {
            addSubBehaviour(new CheckMaterialStorage((ProcurementResponder) interactionBehaviour, dataStore));
        } else if (dataStore.getRequestMessage().getConversationId() == "Take") {
            addSubBehaviour(new GiveMaterialToProduction((ProcurementResponder) interactionBehaviour, dataStore));
        }
    }

}
