package financesBehaviours;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import basicClasses.Order;
import interactors.ResponderBehaviour;
import jade.core.behaviours.OneShotBehaviour;

public class TransferMoneyToBank extends OneShotBehaviour {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	private static final long serialVersionUID = 6133055540457867642L;
	private ResponderBehaviour interactionBehaviour;
	private String orderToSell;
	private String orderText;

	public TransferMoneyToBank(ResponderBehaviour interactionBehaviour) {
		this.interactionBehaviour = interactionBehaviour;
		orderToSell = interactionBehaviour.getRequest().getContent();
	}

	@Override
	public void action() {
		Order order = Order.gson.fromJson(orderToSell, Order.class);
		orderText = order.getTextOfOrder();

		logger.info("Sell {}", orderText);
		interactionBehaviour.getRequestResult().execute(interactionBehaviour.getRequest());
	}
}
