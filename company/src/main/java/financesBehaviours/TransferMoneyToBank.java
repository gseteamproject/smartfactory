package financesBehaviours;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import basicClasses.Order;
import interactors.OrderDataStore;
import jade.core.behaviours.OneShotBehaviour;

public class TransferMoneyToBank extends OneShotBehaviour {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	private static final long serialVersionUID = 6133055540457867642L;
	private FinancesResponder interactionBehaviour;
	private OrderDataStore dataStore;
	private String orderToSell;
	private String orderText;

	public TransferMoneyToBank(FinancesResponder interactionBehaviour, OrderDataStore dataStore) {
		this.interactionBehaviour = interactionBehaviour;
		this.dataStore = dataStore;
		orderToSell = interactionBehaviour.getRequest().getContent();
	}

	@Override
	public void action() {
		Order order = Order.gson.fromJson(orderToSell, Order.class);
		orderText = order.getTextOfOrder();

		logger.info("Sell {}", orderText);
		dataStore.getRequestResult().execute(interactionBehaviour.getRequest());
	}
}
