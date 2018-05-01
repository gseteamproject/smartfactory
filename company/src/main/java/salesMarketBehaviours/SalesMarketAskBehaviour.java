package salesMarketBehaviours;

import basicAgents.SalesMarket;
import basicClasses.Order;
import interactors.AskBehaviour;
import interactors.OrderDataStore;

public class SalesMarketAskBehaviour extends AskBehaviour {

    /**
     * 
     */
    private static final long serialVersionUID = 974888961701937616L;

    public SalesMarketAskBehaviour(SalesMarketResponder interactionBehaviour, OrderDataStore dataStore) {
        super(interactionBehaviour, dataStore);
    }

    @Override
    public void action() {
        if (!this.isStarted()) {
            Order order = Order.gson.fromJson(dataStore.getRequestMessage().getContent(), Order.class);
            if (!SalesMarket.orderQueue.contains(order)) {
                SalesMarket.orderQueue.add(order);
                SalesMarket.orderQueue.get(order.searchInList(SalesMarket.orderQueue)).agent = interactionBehaviour
                        .getAgent().getLocalName();
                // if agent agrees it starts executing request
                // myAgent.addBehaviour(new SalesMarketActivityBehaviour((SalesMarketResponder)
                // interactionBehaviour, (SalesMarketRequestResult) interactor, dataStore));
                myAgent.addBehaviour(new AskForOrderBehaviour((SalesMarketResponder) interactionBehaviour, dataStore));
            }
            this.setStarted(true);
        }
    }
}
