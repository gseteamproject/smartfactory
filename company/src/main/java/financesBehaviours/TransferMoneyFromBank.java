package financesBehaviours;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import basicClasses.Order;
import interactors.ResponderBehaviour;
import jade.core.behaviours.OneShotBehaviour;

public class TransferMoneyFromBank extends OneShotBehaviour {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	private static final long serialVersionUID = -8355136234160671855L;
	private ResponderBehaviour interactionBehaviour;
	private String orderToBuy;
	private String orderText;

	public TransferMoneyFromBank(ResponderBehaviour interactionBehaviour) {
		this.interactionBehaviour = interactionBehaviour;
		orderToBuy = interactionBehaviour.getRequest().getContent();
	}

	@Override
	public void action() {
		Order order = Order.gson.fromJson(orderToBuy, Order.class);
		orderText = order.getTextOfOrder();

		logger.info("Buy {}", orderText);
		interactionBehaviour.getRequestResult().execute(interactionBehaviour.getRequest());
	}
}
