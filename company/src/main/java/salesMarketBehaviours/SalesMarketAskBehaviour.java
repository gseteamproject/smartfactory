package salesMarketBehaviours;

import basicClasses.CrossAgentData;
import basicClasses.Order;
import common.AgentDataStore;
import interactors.AskBehaviour;
import interactors.ResponderBehaviour;

public class SalesMarketAskBehaviour extends AskBehaviour {

	private static final long serialVersionUID = 974888961701937616L;

	public SalesMarketAskBehaviour(ResponderBehaviour interactionBehaviour, AgentDataStore dataStore) {
		super(interactionBehaviour, dataStore);
	}

	@Override
	public void action() {
		if (!this.isStarted()) {
			Order order = Order.fromJson(interactionBehaviour.getRequest().getContent());
			if (!CrossAgentData.orderQueue.contains(order)) {
				CrossAgentData.orderQueue.add(order);
				CrossAgentData.orderQueue
						.get(order.searchInList(CrossAgentData.orderQueue)).agent = interactionBehaviour.getAgent()
								.getLocalName();
				// if agent agrees it starts executing request
				myAgent.addBehaviour(new AskForOrderBehaviour(interactionBehaviour, dataStore));
			}
			this.setStarted(true);
		}
	}
}
