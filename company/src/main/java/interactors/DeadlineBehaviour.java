package interactors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import basicClasses.CrossAgentData;
import basicClasses.Order;
import common.AgentDataStore;
import communication.MessageObject;
import jade.core.behaviours.WakerBehaviour;

public class DeadlineBehaviour extends WakerBehaviour {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	private static final long serialVersionUID = 3660229129526762982L;

	protected ResponderBehaviour interactionBehaviour;

	protected AgentDataStore dataStore;

	public DeadlineBehaviour(ResponderBehaviour interactionBehaviour, AgentDataStore dataStore) {
		super(interactionBehaviour.getAgent(), 60000);
		this.interactionBehaviour = interactionBehaviour;
		this.dataStore = dataStore;
	}

	@Override
	protected void onWake() {
		Order order = dataStore.getOrder();
		logger.info("{}", CrossAgentData.orderQueue);
		logger.info("{}", interactionBehaviour.getRequest().getContent());
		dataStore.setDeadlineResult(true);
		if (order.searchInList(CrossAgentData.orderQueue) > -1) {
			logger.info("Deadline of {}", interactionBehaviour.getAgent().getLocalName());
			interactionBehaviour
					.setResult(interactionBehaviour.getRequestResult().execute(interactionBehaviour.getRequest()));
			if (CrossAgentData.orderQueue.remove(order)) {
				MessageObject msgObj = new MessageObject(interactionBehaviour.getAgent().getLocalName(),
						order.getTextOfOrder() + " is removed from Order queue.");
				dataStore.getAgentPlatform().sendMessageToWebClient(msgObj);
			}
		}
	}
}
