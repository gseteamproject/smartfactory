package salesMarketBehaviours;

import basicAgents.SalesMarketAgent;
import basicClasses.Order;
import communication.MessageObject;
import interactors.OrderDataStore;
import interactors.ResponderBehaviour;
import jade.core.behaviours.OneShotBehaviour;

public class AskForOrderBehaviour extends OneShotBehaviour {

	private static final long serialVersionUID = 8296971392230921846L;
	private OrderDataStore dataStore;
	private ResponderBehaviour interactionBehaviour;
	public MessageObject msgObj;

	public AskForOrderBehaviour(ResponderBehaviour interactionBehaviour, OrderDataStore dataStore) {
		super(interactionBehaviour.getAgent());
		this.interactionBehaviour = interactionBehaviour;
		this.dataStore = dataStore;
	}

	@Override
	public void action() {
		// dataStore.setRequestMessage(interactionBehaviour.getRequest());
		Order order = Order.gson.fromJson(dataStore.getRequestMessage().getContent(), Order.class);
		if (order.searchInList(SalesMarketAgent.orderQueue) > -1) {
			myAgent.addBehaviour(new AskForOrderInitiatorBehaviour(interactionBehaviour, dataStore));
		}
	}
}
