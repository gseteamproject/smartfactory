package financesBehaviours;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import basicClasses.Order;
import interactors.OrderDataStore;
import jade.core.behaviours.OneShotBehaviour;

public class TransferMoneyFromBank extends OneShotBehaviour {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	private static final long serialVersionUID = -8355136234160671855L;
    private FinancesResponder interactionBehaviour;
    private OrderDataStore dataStore;
    private String orderToBuy;
    private String orderText;

    public TransferMoneyFromBank(FinancesResponder interactionBehaviour, OrderDataStore dataStore) {
        this.interactionBehaviour = interactionBehaviour;
        this.dataStore = dataStore;
        orderToBuy = interactionBehaviour.getRequest().getContent();
    }

    @Override
    public void action() {
        Order order = Order.gson.fromJson(orderToBuy, Order.class);
        orderText = order.getTextOfOrder();

		logger.info("Buy {}", orderText);
        dataStore.getRequestResult().execute(interactionBehaviour.getRequest());
    }
}
