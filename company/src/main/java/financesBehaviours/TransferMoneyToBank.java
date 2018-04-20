package financesBehaviours;

import basicClasses.Order;
import interactors.OrderDataStore;
import jade.core.behaviours.OneShotBehaviour;

public class TransferMoneyToBank extends OneShotBehaviour {

    /**
     * 
     */
    private static final long serialVersionUID = 6133055540457867642L;
    private FinancesResponder interactionBehaviour;
    private FinancesRequestResult interactor;
    private OrderDataStore dataStore;
    private String orderToSell;
    private String orderText;

    public TransferMoneyToBank(FinancesResponder interactionBehaviour, OrderDataStore dataStore) {
        this.interactionBehaviour = interactionBehaviour;
        this.interactor = FinancesResponder.interactor;
        this.dataStore = dataStore;
        orderToSell = interactionBehaviour.getRequest().getContent();
    }

    @Override
    public void action() {
        Order order = Order.gson.fromJson(orderToSell, Order.class);
        orderText = order.getTextOfOrder();

        System.out.println("Sell " + orderText);
        interactor.execute(interactionBehaviour.getRequest());
    }
}
