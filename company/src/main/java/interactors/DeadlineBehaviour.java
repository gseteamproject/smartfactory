package interactors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import basicAgents.SalesMarketAgent;
import basicClasses.Order;
import communication.Communication;
import communication.MessageObject;
import communication.Server;
import jade.core.behaviours.WakerBehaviour;

public class DeadlineBehaviour extends WakerBehaviour {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	private static final long serialVersionUID = 3660229129526762982L;

	protected ResponderBehaviour interactionBehaviour;
	protected OrderDataStore dataStore;

	public DeadlineBehaviour(ResponderBehaviour interactionBehaviour, OrderDataStore dataStore) {
		// super(interactionBehaviour.getAgent(), dataStore.getDeadline() *
		// Server.delaytime / 150);
		super(interactionBehaviour.getAgent(), 60000);
		this.interactionBehaviour = interactionBehaviour;
		this.dataStore = dataStore;
	}

	@Override
	protected void onWake() {
		logger.info("{}", dataStore.getDeadline() * Server.delaytime / 150);
		Order order = Order.gson.fromJson(dataStore.getRequestMessage().getContent(), Order.class);
		logger.info("{}", SalesMarketAgent.orderQueue);
		logger.info("{}", dataStore.getRequestMessage().getContent());
		dataStore.setDeadlineResult(true);
		if (order.searchInList(SalesMarketAgent.orderQueue) > -1) {
			logger.info("Deadline of {}", interactionBehaviour.getAgent().getLocalName());
			interactionBehaviour.setResult(dataStore.getRequestResult().execute(interactionBehaviour.getRequest()));
			if (SalesMarketAgent.orderQueue.remove(order)) {
				MessageObject msgObj = new MessageObject(interactionBehaviour.getAgent().getLocalName(),
						order.getTextOfOrder() + " is removed from Order queue.");
				Communication.server.sendMessageToClient(msgObj);
				/*
				 * System.out.println("SalesMarketAgent: " + orderText +
				 * " is removed from Order queue.");
				 */
			}
		}
	}
}
