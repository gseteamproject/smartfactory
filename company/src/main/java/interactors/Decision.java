package interactors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import application.Main;
import basicClasses.Order;
import common.AgentDataStore;
import communication.MessageObject;
import jade.lang.acl.ACLMessage;

public class Decision {

	protected AgentDataStore dataStore;

	protected ResponderBehaviour interactionBehaviour;

	protected Order order;

	public Decision(ResponderBehaviour interactionBehaviour, AgentDataStore dataStore) {
		this.dataStore = dataStore;
		this.interactionBehaviour = interactionBehaviour;
	}

	public ACLMessage execute(ACLMessage request) {
		return null;
	}

	public void setup(ACLMessage request) {
		order = Order.fromJson(request.getContent());
		dataStore.setOrder(order);

		final String agentName = dataStore.getAgentName();
		logger.info("currentAgent: {}", agentName);
		order.agent = agentName;

		String orderGson = order.toJson();
		request.setContent(orderGson);

		MessageObject msgObj = new MessageObject(request, order.getTextOfOrder());
		dataStore.getAgentPlatform().sendMessageToWebClient(msgObj);

		long timeout = (order.deadline - System.currentTimeMillis()) * Main.SERVER_DELAY_TIME / 150;
		logger.info("currentDeadline: {}", timeout);

		interactionBehaviour.getDeadlineBehaviour().reset(timeout);
		dataStore.setDeadlineResult(false);
		interactionBehaviour.getRequestResult().reset();
		interactionBehaviour.getAskBehaviour().reset();
	}

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
}
