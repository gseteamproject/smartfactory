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

		// Agent should send agree or refuse
		// TODO: Add refuse answer (some conditions should be added)

		dataStore.setAgentName(interactionBehaviour.getAgent().getLocalName());
		logger.info("currentAgent: {}", dataStore.getAgentName());

		if (dataStore.getAgentName().equals("AgentSalesMarket")) {
			dataStore.setDeadline(order.deadline);
			long currentDeadline = System.currentTimeMillis() + order.deadline;
			order.deadline = currentDeadline;
		} else {
			dataStore.setDeadline(order.deadline - System.currentTimeMillis());
		}

		logger.info("currentDeadline: {}", order.deadline);

		order.agent = dataStore.getAgentName();

		String orderGson = order.toJson();
		request.setContent(orderGson);

		MessageObject msgObj = new MessageObject(request, order.getTextOfOrder());
		dataStore.getAgentPlatform().sendMessageToWebClient(msgObj);
		dataStore.setDeadlineResult(false);

		interactionBehaviour.getDeadlineBehaviour().reset(dataStore.getDeadline() * Main.SERVER_DELAY_TIME / 150);
		interactionBehaviour.getRequestResult().reset();
		interactionBehaviour.getAskBehaviour().setStarted(false);
	}

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
}
