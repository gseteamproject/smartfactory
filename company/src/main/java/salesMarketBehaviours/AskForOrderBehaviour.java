package salesMarketBehaviours;

import basicClasses.CrossAgentData;
import basicClasses.Order;
import common.AgentDataStore;
import communication.MessageObject;
import interactors.ResponderBehaviour;
import jade.core.behaviours.OneShotBehaviour;

public class AskForOrderBehaviour extends OneShotBehaviour {

	private static final long serialVersionUID = 8296971392230921846L;
	private AgentDataStore dataStore;
	private ResponderBehaviour interactionBehaviour;
	public MessageObject msgObj;

	public AskForOrderBehaviour(ResponderBehaviour interactionBehaviour, AgentDataStore dataStore) {
		super(interactionBehaviour.getAgent());
		this.interactionBehaviour = interactionBehaviour;
		this.dataStore = dataStore;
	}

	@Override
	public void action() {
		// dataStore.setRequestMessage(interactionBehaviour.getRequest());
		Order order = Order.gson.fromJson(dataStore.getRequestMessage().getContent(), Order.class);
		if (order.searchInList(CrossAgentData.orderQueue) > -1) {
			myAgent.addBehaviour(new AskForOrderInitiatorBehaviour(interactionBehaviour, dataStore));
		}
	}
}
