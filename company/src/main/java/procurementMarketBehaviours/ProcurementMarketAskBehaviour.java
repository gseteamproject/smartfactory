package procurementMarketBehaviours;

import basicAgents.SalesMarketAgent;
import basicClasses.Order;
import interactors.AskBehaviour;
import interactors.OrderDataStore;

public class ProcurementMarketAskBehaviour extends AskBehaviour {

    /**
     * 
     */
    private static final long serialVersionUID = -121063921816472827L;

    public ProcurementMarketAskBehaviour(ProcurementMarketResponder interactionBehaviour, OrderDataStore dataStore) {
        super(interactionBehaviour, dataStore);
    }

    @Override
    public void action() {
        if (!this.isStarted()) {
            Order order = Order.gson.fromJson(interactionBehaviour.getRequest().getContent(), Order.class);
            if (order.searchInList(SalesMarketAgent.orderQueue) > -1) {
                // if (!this.isStarted()) {
                SalesMarketAgent.orderQueue.get(order.searchInList(SalesMarketAgent.orderQueue)).agent = interactionBehaviour
                        .getAgent().getLocalName();

                // myAgent.addBehaviour(new
                // ProcurementMarketActivityBehaviour((ProcurementMarketResponder)
                // interactionBehaviour, (ProcurementMarketRequestResult) interactor,
                // dataStore));
                // myAgent.addBehaviour(new AuctionInitiator((ProcurementMarketResponder)
                // interactionBehaviour));
                myAgent.addBehaviour(
                        new ReportFinancesBehaviour((ProcurementMarketResponder) interactionBehaviour, dataStore));
                // }
                // this.setStarted(true);
            }
            this.setStarted(true);
        }
    }
}
