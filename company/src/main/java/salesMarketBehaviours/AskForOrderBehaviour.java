package salesMarketBehaviours;

import communication.MessageObject;
import interactors.OrderDataStore;
import jade.core.behaviours.OneShotBehaviour;

public class AskForOrderBehaviour extends OneShotBehaviour {

    /**
     * 
     */
    private static final long serialVersionUID = 8296971392230921846L;
    private String orderToRequest;
    private OrderDataStore dataStore;
    private SalesMarketResponder interactionBehaviour;
    public MessageObject msgObj;

    public AskForOrderBehaviour(SalesMarketResponder interactionBehaviour, OrderDataStore dataStore) {
        super(interactionBehaviour.getAgent());
        this.interactionBehaviour = interactionBehaviour;
        this.dataStore = dataStore;
    }

    @Override
    public void action() {
        orderToRequest = interactionBehaviour.getRequest().getContent();
        dataStore.setRequestMessage(interactionBehaviour.getRequest());
        myAgent.addBehaviour(new AskForOrderInitiatorBehaviour(interactionBehaviour, dataStore));
    }
}