package financesBehaviours;

import basicClasses.Order;
import interactors.OrderDataStore;
import jade.core.behaviours.OneShotBehaviour;

public class TransferMoneyFromBank extends OneShotBehaviour {

    /**
     * 
     */
    private static final long serialVersionUID = -8355136234160671855L;
    private FinancesResponder interactionBehaviour;
    private FinancesRequestResult interactor;
    // private OrderDataStore dataStore;
    private String orderToBuy;
    private String orderText;

    public TransferMoneyFromBank(FinancesResponder interactionBehaviour, OrderDataStore dataStore) {
        this.interactionBehaviour = interactionBehaviour;
        this.interactor = FinancesResponder.interactor;
        // this.dataStore = dataStore;
        orderToBuy = interactionBehaviour.getRequest().getContent();
    }

    @Override
    public void action() {
        Order order = Order.gson.fromJson(orderToBuy, Order.class);
        orderText = order.getTextOfOrder();

        System.out.println("Buy " + orderText);
        interactor.execute(interactionBehaviour.getRequest());
    }
}
