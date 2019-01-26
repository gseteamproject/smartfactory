package interactors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import application.Main;
import basicClasses.Order;
import common.AgentDataStore;
import communication.MessageObject;
import jade.lang.acl.ACLMessage;

public class Decision {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	protected AgentDataStore dataStore;

	protected ResponderBehaviour interactionBehaviour;

	protected String orderText;

	protected MessageObject msgObj;

	protected ACLMessage response;

	public Decision(ResponderBehaviour interactionBehaviour, AgentDataStore dataStore) {
		this.dataStore = dataStore;
		this.interactionBehaviour = interactionBehaviour;
	}

	public ACLMessage execute(ACLMessage request) {
		return null;
	}

	public void setup(ACLMessage request) {
		Order order = Order.gson.fromJson(request.getContent(), Order.class);
		orderText = order.getTextOfOrder();

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

		String orderGson = Order.gson.toJson(order);
		request.setContent(orderGson);

		dataStore.setRequestMessage(request);

		msgObj = new MessageObject(request, orderText);
		dataStore.getAgentPlatform().sendMessageToWebClient(msgObj);
		dataStore.setDeadlineResult(false);

		interactionBehaviour.getDeadlineBehaviour().reset(dataStore.getDeadline() * Main.SERVER_DELAY_TIME / 150);

		interactionBehaviour.getRequestResult().reset();
		interactionBehaviour.getAskBehaviour().setStarted(false);
	}
}
