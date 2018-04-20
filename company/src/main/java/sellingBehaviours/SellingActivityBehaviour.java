package sellingBehaviours;

import interactors.DeadlineBehaviour;
import interactors.OrderDataStore;
import jade.core.behaviours.ParallelBehaviour;

public class SellingActivityBehaviour extends ParallelBehaviour {

    /**
     * 
     */
    private static final long serialVersionUID = 5504974627813962693L;

    // TODO: put this into DataStore

    public SellingActivityBehaviour(SellingResponder interactionBehaviour, SellingRequestResult interactor,
            OrderDataStore dataStore) {
        super(interactionBehaviour.getAgent(), WHEN_ANY);
        // interactor = new SellingRequestResult(dataStore);

        // addSubBehaviour(new SellingAskBehaviour(interactionBehaviour, interactor,
        // dataStore));

        addSubBehaviour(new DeadlineBehaviour((SellingResponder) interactionBehaviour, (SellingRequestResult) interactor, dataStore));
//        addSubBehaviour(new SellingDeadlineBehaviour(interactionBehaviour, interactor, dataStore));
        if (dataStore.getRequestMessage().getConversationId() == "Ask") {
            addSubBehaviour(new CheckWarehouseBehaviour(interactionBehaviour, dataStore));
        } else if (dataStore.getRequestMessage().getConversationId() == "Take") {
            addSubBehaviour(new GiveProductToMarketBehaviour(interactionBehaviour, dataStore));
        }
    }

}
