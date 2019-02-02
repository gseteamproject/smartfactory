package salesMarketBehaviours;

import basicClasses.CrossAgentData;
import basicClasses.Order;
import common.AgentDataStore;
import interactors.ResponderBehaviour;
import jade.core.behaviours.OneShotBehaviour;

public class AskForOrderBehaviour extends OneShotBehaviour {

	private static final long serialVersionUID = 8296971392230921846L;

	private AgentDataStore dataStore;

	private ResponderBehaviour interactionBehaviour;

	public AskForOrderBehaviour(ResponderBehaviour interactionBehaviour, AgentDataStore dataStore) {
		super(null);
		this.interactionBehaviour = interactionBehaviour;
		this.dataStore = dataStore;
	}

	@Override
	public void action() {
		Order order = dataStore.getOrder();
		if (order.searchInList(CrossAgentData.orderQueue) > -1) {
			myAgent.addBehaviour(new AskForOrderInitiatorBehaviour(interactionBehaviour, dataStore));
		}
	}
}
