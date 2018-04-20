package procurementMarketBehaviours;

import basicAgents.SalesMarket;
import basicClasses.Order;
import interactors.AskBehaviour;
import interactors.OrderDataStore;

public class ProcurementMarketAskBehaviour extends AskBehaviour {

    /**
     * 
     */
    private static final long serialVersionUID = -121063921816472827L;

    public ProcurementMarketAskBehaviour(ProcurementMarketResponder interactionBehaviour,
            ProcurementMarketRequestResult interactor, OrderDataStore dataStore) {
        super(interactionBehaviour, interactor, dataStore);
    }

    @Override
    public void action() {
        if (!this.isStarted) {
            // TODO: pass DataStore?
            Order order = Order.gson.fromJson(interactionBehaviour.getRequest().getContent(), Order.class);

            SalesMarket.orderQueue.get(order.searchInList(SalesMarket.orderQueue)).agent = interactionBehaviour.getAgent().getLocalName();
            
            myAgent.addBehaviour(new ProcurementMarketActivityBehaviour((ProcurementMarketResponder) interactionBehaviour, (ProcurementMarketRequestResult) interactor, dataStore));
//            myAgent.addBehaviour(new AuctionInitiator((ProcurementMarketResponder) interactionBehaviour));
        }
        this.isStarted = true;
    }
}
